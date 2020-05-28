package com.example.automobile_rest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Automobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String modello;
	private String targa;
	@Temporal(TemporalType.DATE)
	private Date dataImmatricolazione;
	private Boolean inProduzione;

	public Automobile() {
	}
	
	public Automobile(String marca, String modello, String targa, Date dataImmatricolazione, Boolean inProduzione) {
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.dataImmatricolazione = dataImmatricolazione;
		this.inProduzione = inProduzione;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Date getDataImmatricolazione() {
		return dataImmatricolazione;
	}

	public void setDataImmatricolazione(Date dataImmatricolazione) {
		this.dataImmatricolazione = dataImmatricolazione;
	}

	public Boolean getInProduzione() {
		return inProduzione;
	}

	public void setInProduzione(Boolean inProduzione) {
		this.inProduzione = inProduzione;
	}

	@Override
	public String toString() {
		return "Automobile [id="+id+", marca=" + marca + ", modello=" + modello + ", targa=" + targa + ", dataImmatricolazione="
				+ dataImmatricolazione + ", inProduzione=" + inProduzione + "]";
	}
	
	

}
