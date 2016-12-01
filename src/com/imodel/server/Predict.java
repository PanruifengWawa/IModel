package com.imodel.server;

import java.util.Map;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

import com.imodel.models.Model;
import com.imodel.utils.NumberCheck;

public class Predict {
	private Model model;
	private Map<String, String[]> keys;
	public Predict(Model model,Map<String, String[]> keys) {
		this.model = model;
		this.keys = keys;
	}
	public String predict() {
		String result = "unknow";
		switch (model.getAlgorithm()) {
		case "C50":
			result = predictC50();
			break;
		case "CART":
			result = predictCart();
			break;
		default:
			break;
		}
		return result;
	}
	
	private String predictCart() {
		String result = "";
		RConnection re = null ;
		try {
			re = new RConnection();
			re.eval("setwd('" + model.getFileDir() + "')");
			String resultfile = model.getFileName() + "_CART.RData";
			
			re.assign("resultfile", resultfile);
			re.eval("library(rpart)");
			re.eval("load(resultfile)");
			
			String[] inputs = model.getModelInput().split(",");
			for(String input : inputs) {
				String[] value = keys.get(input);
				if (value == null || value.length == 0 || value[0].equals("")) {
					re.close();
					return "Lack Inputs";
				}
				re.eval("Age=36");
				re.eval("Sex='M'");
				if (NumberCheck.isNumber(value[0])) {
					re.eval(input + "=" + value[0]);
				} else {
					re.eval(input + "='" + value[0] + "'");
				}
			}
			
			re.eval("test = data.frame(" + model.getModelInput() + ")");
			
			re.eval("cart_pred <- predict(cart_model, test,type='class')");
			result = re.eval("cart_pred").asString();
			
		} catch (REngineException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			re.close();
		}
		return result;
	}
	
	private String predictC50() {
		String result = "";
		RConnection re = null ;
		try {
			re = new RConnection();
			re.eval("setwd('" + model.getFileDir() + "')");
			String resultfile = model.getFileName() + "_CS_5.RData";
			String[] inputs = model.getModelInput().split(",");
			for(String input : inputs) {
				String[] value = keys.get(input);
				if (value == null || value.length == 0 || value[0].equals("")) {
					re.close();
					return "Lack Inputs";
				}
				re.assign(input,value[0]);
			}
			
			re.assign("resultfile", resultfile);
			re.eval("library(C50)");
			
			re.eval("load(resultfile)");
			re.eval("test = data.frame(" + model.getModelInput() + ")");
			
			re.eval("credit_pred <- predict(credit_model, test)");
			result = re.eval("credit_pred").asString();
			
		} catch (REngineException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}finally {
			re.close();
		}
		
		return result;
	}

}
