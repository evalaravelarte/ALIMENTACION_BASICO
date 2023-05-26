package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * La clase Alimento representa un alimento con sus atributos nutricionales.
 */
@Entity
@Table(name = "alimento")
public class Alimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAlimento")
	private int idAlimento;
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "grasas")
	private double grasas;

	@Column(name = "grasasSaturadas")
	private double grasasSaturadas;

	@Column(name = "hidratos")
	private double hidratos;

	@Column(name = "azucar")
	private double azucar;

	@Column(name = "proteinas")
	private double proteinas;

	@Column(name = "sal")
	private double sal;

	@Column(name = "calorias")
	private double calorias;

	/**
     * Crea una instancia de la clase Alimento sin inicializar los atributos.
     */
	public Alimento() {
		super();
	}

	/**
     * Crea una instancia de la clase Alimento con los atributos especificados.
     *
     * @param nombre           el nombre del alimento
     * @param grasas           la cantidad de grasas en el alimento
     * @param grasasSaturadas  la cantidad de grasas saturadas en el alimento
     * @param hidratos         la cantidad de hidratos de carbono en el alimento
     * @param azucar           la cantidad de azúcar en el alimento
     * @param proteinas        la cantidad de proteínas en el alimento
     * @param sal              la cantidad de sal en el alimento
     * @param calorias         la cantidad de calorías en el alimento
     */
	public Alimento(String nombre, double grasas, double grasasSaturadas, double hidratos, double azucar, double proteinas,
			double sal, double calorias) {
		super();
		this.nombre = nombre;
		this.grasas = grasas;
		this.grasasSaturadas = grasasSaturadas;
		this.hidratos = hidratos;
		this.azucar = azucar;
		this.proteinas = proteinas;
		this.sal = sal;
		this.calorias = calorias;
	}

	/**
     * Obtiene el ID del alimento.
     *
     * @return el ID del alimento
     */
	public int getIdAlimento() {
		return idAlimento;
	}

	/**
     * Establece el ID del alimento.
     *
     * @param idAlimento el ID del alimento a establecer
     */
	public void setIdAlimento(int idAlimento) {
		this.idAlimento = idAlimento;
	}

	/**
     * Obtiene el nombre del alimento.
     *
     * @return el nombre del alimento
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * Establece el nombre del alimento.
     *
     * @param nombre el nombre del alimento a establecer
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


    /**
     * Obtiene la cantidad de grasas en el alimento.
     *
     * @return la cantidad de grasas en el alimento
     */
	public double getGrasas() {
		return grasas;
	}

	 /**
     * Establece la cantidad de grasas en el alimento.
     *
     * @param grasas la cantidad de grasas a establecer
     */
	public void setGrasas(double grasas) {
		this.grasas = grasas;
	}

	 /**
     * Obtiene la cantidad de grasas saturadas en el alimento.
     *
     * @return la cantidad de grasas saturadas en el alimento
     */
	public double getGrasasSaturadas() {
		return grasasSaturadas;
	}

	/**
     * Establece la cantidad de grasas saturadas en el alimento.
     *
     * @param grasasSaturadas la cantidad de grasas saturadas a establecer
     */
	public void setGrasasSaturadas(double grasasSaturadas) {
		this.grasasSaturadas = grasasSaturadas;
	}
	

	/**
     * Obtiene la cantidad de hidratos de carbono en el alimento.
     *
     * @return la cantidad de hidratos de carbono en el alimento
     */
	public double getHidratos() {
		return hidratos;
	}

	/**
     * Establece la cantidad de hidratos de carbono en el alimento.
     *
     * @param hidratos la cantidad de hidratos de carbono a establecer
     */
	public void setHidratos(double hidratos) {
		this.hidratos = hidratos;
	}

	/**
     * Obtiene la cantidad de azúcar en el alimento.
     *
     * @return la cantidad de azúcar en el alimento
     */
	public double getAzucar() {
		return azucar;
	}

	 /**
     * Establece la cantidad de azúcar en el alimento.
     *
     * @param azucar la cantidad de azúcar a establecer
     */
	public void setAzucar(double azucar) {
		this.azucar = azucar;
	}

	/**
     * Obtiene la cantidad de proteínas en el alimento.
     *
     * @return la cantidad de proteínas en el alimento
     */
	public double getProteinas() {
		return proteinas;
	}

	/**
     * Establece la cantidad de proteínas en el alimento.
     *
     * @param proteinas la cantidad de proteínas a establecer
     */
	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}

	/**
     * Obtiene la cantidad de sal en el alimento.
     *
     * @return la cantidad de sal en el alimento
     */
	public double getSal() {
		return sal;
	}

	/**
     * Establece la cantidad de sal en el alimento.
     *
     * @param sal la cantidad de sal a establecer
     */
	public void setSal(double sal) {
		this.sal = sal;
	}


    /**
     * Obtiene la cantidad de calorías en el alimento.
     *
     * @return la cantidad de calorías en el alimento
     */
	public double getCalorias() {
		return calorias;
	}

	/**
     * Establece la cantidad de calorías en el alimento.
     *
     * @param calorias la cantidad de calorías a establecer
     */
	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}
	
}
