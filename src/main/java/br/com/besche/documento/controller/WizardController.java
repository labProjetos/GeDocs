package br.com.besche.documento.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

@ViewScoped
@Named(value = "wizardController")
public class WizardController implements Serializable {
	private static final long serialVersionUID = 1L;

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}
}
