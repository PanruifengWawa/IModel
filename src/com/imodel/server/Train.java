package com.imodel.server;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

import com.imodel.models.Model;
import com.imodel.utils.RestfulAPIUtil;

public class Train extends Thread {
	
	private Model model;
	public Train(Model model) {
		this.model = model;
	}
	

	public void run() {
		String url = "http://localhost:8080/IModel/v1/api/model/updateModelsFrom?id=" + model.getId();
		switch (model.getAlgorithm()) {
		case "C50":
			startC50();
			break;
		case "CART":
			startCART();
			break;
		default:
			break;
		}
		RestfulAPIUtil.sendGet(url + "&size=" + model.getSize());
	}
	
	private void startCART() {
		RConnection re = null ;
		try {
			re = new RConnection();

			re.eval("setwd('" + model.getFileDir() + "')");
			String file = model.getFileName();
			String resultfile = model.getFileName() + "_CART.RData";
			re.assign("file",file);
			re.assign("resultfile", resultfile);
			re.eval("library(rpart)");
			re.eval("dataset = read.csv(file,header=T)");
			re.eval("size = nrow(dataset)");
			String args = model.getModelTarget() + "~" + model.getModelInput().replaceAll(",", "+");
			re.eval("cart_model=rpart(" + args + ",method='class',data=dataset)");
			re.eval("save(cart_model,file=resultfile)");
			
			Integer size = re.eval("size").asInteger();
			model.setSize(size);
			
		} catch (REngineException | REXPMismatchException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();

		}finally {
			re.close();
		}
	}
	
	private void startC50(){
		RConnection re = null ;
		try {
			re = new RConnection();
			re.eval("setwd('" + model.getFileDir() + "')");
			String file = model.getFileName();
			String resultfile = model.getFileName() + "_CS_5.RData";
			String target = model.getModelTarget();
			re.assign("file",file);
			re.assign("resultfile", resultfile);
			re.assign("target", target);
			re.eval("library(C50)");
			re.eval("data = read.csv(file,header=T)");
			re.eval("size = nrow(data)");
			re.eval("position = which(names(data)==target)");
			
			re.eval("credit_model <- C5.0(data[-position], data$" + target + ")");
			re.eval("save(credit_model,file=resultfile)");
			
			Integer size = re.eval("size").asInteger();
			model.setSize(size);

		} catch (REngineException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}finally {
			re.close();
		}
	}

}
