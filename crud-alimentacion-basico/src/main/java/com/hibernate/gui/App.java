package com.hibernate.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.AlimentoDAO;
import com.hibernate.model.Alimento;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.util.HashMap;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;

/**
 * Clase App: interfaz gráfica mediante la que se interacciona con las clases
 * DAO las cuales interaccionan con la base de datos
 * 
 * @author eva lara velarte y jose ayala farell
 *
 */
public class App {

	private JFrame frmAlimentacion;
	private JTable tableAlimentos;
	private JTextField textFieldIdAlimento;
	private JTextField textFieldNombre;
	private JTextField textFieldGrasas;
	private JTextField textFieldGrasasSat;
	private JTextField textFieldAzucar;
	private JTextField textFieldHidratos;
	private JTextField textFieldProteinas;
	private JTextField textFieldSal;
	private JTextField textFieldCalorias;
	private JTextField textFieldCantidad;

	String erNombre = "^[A-Za-záéíóúÁÉÍÓÚñÑüÜ\\s]+$";

	/**
	 * Comprueba si una palabra coincide con una expresión regular dada.
	 *
	 * @param palabra La palabra a verificar.
	 * @param er      La expresión regular a utilizar para la verificación.
	 * @return true si la palabra coincide con la expresión regular, false de lo
	 *         contrario.
	 */
	boolean comprobarExpReg(String palabra, String er) {
		Pattern pat = Pattern.compile(er);
		Matcher mat = pat.matcher(palabra);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}

	// Variables para sumar las variables de los alimentos
	private double grasas = 0;
	private double grasasSat = 0;
	private double hidratos = 0;
	private double azucar = 0;
	private double proteinas = 0;
	private double sal = 0;
	private double calorias = 0;

	private JTable tableTotalComido;
	private JTextField textFieldGrasasTotales;
	private JTextField textFieldGrasasSatTotal;
	private JTextField textFieldAzucarTotal;
	private JTextField textFieldHidratosTotal;
	private JTextField textFieldProteinasTotal;
	private JTextField textFieldSalTotal;
	private JTextField textFieldCaloriastotal;

	static final int MAX_SAL = 5;
	static final int MAX_AZUCAR = 22;
	static final int MAX_KCAL = 200;
	static final double EJER_POCO = 1.2;
	static final double EJER_LIGERO = 1.4;
	static final double EJER_MODERADO = 1.6;
	static final double EJER_FUERTE = 1.8;
	static final double EJER_MUY_FUERTE = 2;

	/**
	 * Función calcularCantidad para que partiendo de los datos de un alimento cada
	 * 100g, obtenganmos los valores correspondientes a la cantidad.
	 * 
	 * @param variable: dato de un alimento
	 * @param cantidad: cantidad de alimento que se ha comido
	 * @return devuelve el valor correspondiente del dato del alimento en función de
	 *         la cantidad
	 */
	static double calcularCantidad(double variable, double cantidad) {

		return (cantidad * variable) / 100;

	}

	/**
	 * Función para calcular las calorias que debe ingerir una persona al día
	 * 
	 * @param peso:    peso de la persona
	 * @param factAct: factor de actividad, cantidad de ejercicio que realiza
	 * @return devulve las calorías que debe comer
	 */
	static double calcularCalorias(double peso, double factAct) {

		return peso * 22 * factAct;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmAlimentacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAlimentacion = new JFrame();
		frmAlimentacion.setTitle("ALIMENTACION");
		frmAlimentacion.setBounds(100, 100, 1423, 843);
		frmAlimentacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlimentacion.getContentPane().setLayout(null);

		AlimentoDAO alimentoDAO = new AlimentoDAO();
		HashMap<String, Double> alimentosComidos = new HashMap<>();

		// Creación la tabla alimentos
		DefaultTableModel modelAlimentos = new DefaultTableModel();
		modelAlimentos.addColumn("Id");
		modelAlimentos.addColumn("Nombre");
		modelAlimentos.addColumn("Grasas");
		modelAlimentos.addColumn("Grasas Saturadas");
		modelAlimentos.addColumn("Hidratos");
		modelAlimentos.addColumn("Azúcares");
		modelAlimentos.addColumn("Proteína");
		modelAlimentos.addColumn("Sal");
		modelAlimentos.addColumn("Calorías");

		// Creación la tabla alimentos comidos
		DefaultTableModel modelAlimentosComidos = new DefaultTableModel();
		modelAlimentosComidos.addColumn("Alimento");
		modelAlimentosComidos.addColumn("Cantidad");

		DefaultTableCellRenderer centrador = new DefaultTableCellRenderer();
		centrador.setHorizontalAlignment(SwingConstants.CENTER);

		tableAlimentos = new JTable(modelAlimentos);
		tableAlimentos.setSelectionBackground(new Color(244, 164, 96));
		tableAlimentos.setBackground(new Color(255, 228, 196));
		tableAlimentos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableAlimentos.setBounds(113, 270, 432, 175);
		frmAlimentacion.getContentPane().add(tableAlimentos);
		tableAlimentos.setDefaultEditor(Object.class, null);
		for (int i = 0; i < tableAlimentos.getColumnCount(); i++) {
			tableAlimentos.getColumnModel().getColumn(i).setCellRenderer(centrador);
		}

		JScrollPane scrollPaneAlimentos = new JScrollPane(tableAlimentos);
		scrollPaneAlimentos.setBounds(100, 40, 1106, 231);
		frmAlimentacion.getContentPane().add(scrollPaneAlimentos);

		tableTotalComido = new JTable(modelAlimentosComidos);
		tableTotalComido.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableTotalComido.setBounds(755, 647, 363, -114);
		frmAlimentacion.getContentPane().add(tableTotalComido);
		tableTotalComido.setDefaultEditor(Object.class, null);
		for (int i = 0; i < tableTotalComido.getColumnCount(); i++) {
			tableTotalComido.getColumnModel().getColumn(i).setCellRenderer(centrador);
		}

		JScrollPane scrollPaneAlimentosComidos = new JScrollPane(tableTotalComido);
		scrollPaneAlimentosComidos.setBounds(653, 483, 221, 98);
		frmAlimentacion.getContentPane().add(scrollPaneAlimentosComidos);

		// Cambiar color del encabezado de la tabla
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(new Color(255, 165, 0));
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < tableAlimentos.getColumnModel().getColumnCount(); i++) {
			tableAlimentos.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}

		for (int i = 0; i < tableTotalComido.getColumnModel().getColumnCount(); i++) {
			tableTotalComido.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}

		JButton btnCrear = new JButton("Crear");
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Dialog", Font.BOLD, 11));
		btnCrear.setBackground(new Color(255, 165, 0));
		btnCrear.setBounds(447, 319, 106, 21);
		frmAlimentacion.getContentPane().add(btnCrear);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Dialog", Font.BOLD, 11));
		btnActualizar.setBackground(new Color(255, 165, 0));
		btnActualizar.setBounds(447, 388, 106, 21);
		frmAlimentacion.getContentPane().add(btnActualizar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Dialog", Font.BOLD, 11));
		btnBorrar.setBackground(new Color(255, 165, 0));
		btnBorrar.setBounds(447, 463, 106, 21);
		frmAlimentacion.getContentPane().add(btnBorrar);

		JLabel lblIdAlimento = new JLabel("Id:");
		lblIdAlimento.setForeground(new Color(255, 165, 0));
		lblIdAlimento.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIdAlimento.setBounds(100, 302, 73, 13);
		frmAlimentacion.getContentPane().add(lblIdAlimento);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 165, 0));
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setBounds(100, 348, 60, 13);
		frmAlimentacion.getContentPane().add(lblNombre);

		JLabel lblGrasas = new JLabel("Grasas:");
		lblGrasas.setForeground(new Color(255, 165, 0));
		lblGrasas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGrasas.setBounds(100, 391, 99, 13);
		frmAlimentacion.getContentPane().add(lblGrasas);

		JLabel lblGrasasSaturadas = new JLabel("Grasas Saturadas:");
		lblGrasasSaturadas.setForeground(new Color(255, 165, 0));
		lblGrasasSaturadas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGrasasSaturadas.setBounds(100, 430, 141, 13);
		frmAlimentacion.getContentPane().add(lblGrasasSaturadas);

		JLabel lblHidratos = new JLabel("Hidratos:");
		lblHidratos.setForeground(new Color(255, 165, 0));
		lblHidratos.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHidratos.setBounds(100, 466, 73, 13);
		frmAlimentacion.getContentPane().add(lblHidratos);

		JLabel lblAzucares = new JLabel("Azúcares:");
		lblAzucares.setForeground(new Color(255, 165, 0));
		lblAzucares.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAzucares.setBounds(100, 502, 99, 13);
		frmAlimentacion.getContentPane().add(lblAzucares);

		textFieldIdAlimento = new JTextField();
		textFieldIdAlimento.setEditable(false);
		textFieldIdAlimento.setColumns(10);
		textFieldIdAlimento.setBounds(259, 299, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldIdAlimento);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(259, 345, 135, 19);
		frmAlimentacion.getContentPane().add(textFieldNombre);

		textFieldGrasas = new JTextField();
		textFieldGrasas.setColumns(10);
		textFieldGrasas.setBounds(259, 388, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldGrasas);

		textFieldGrasasSat = new JTextField();
		textFieldGrasasSat.setColumns(10);
		textFieldGrasasSat.setBounds(259, 427, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldGrasasSat);

		textFieldAzucar = new JTextField();
		textFieldAzucar.setColumns(10);
		textFieldAzucar.setBounds(259, 499, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldAzucar);

		textFieldHidratos = new JTextField();
		textFieldHidratos.setColumns(10);
		textFieldHidratos.setBounds(259, 463, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldHidratos);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Dialog", Font.BOLD, 11));
		btnLimpiar.setBackground(new Color(255, 165, 0));
		btnLimpiar.setBounds(447, 532, 106, 21);
		frmAlimentacion.getContentPane().add(btnLimpiar);

		JLabel lblProtenias = new JLabel("Proteínas:");
		lblProtenias.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProtenias.setForeground(new Color(255, 165, 0));
		lblProtenias.setBounds(100, 538, 85, 15);
		frmAlimentacion.getContentPane().add(lblProtenias);

		textFieldProteinas = new JTextField();
		textFieldProteinas.setBounds(259, 536, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldProteinas);
		textFieldProteinas.setColumns(10);

		JLabel lblSal = new JLabel("Sal:");
		lblSal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSal.setForeground(new Color(255, 165, 0));
		lblSal.setBounds(100, 565, 70, 15);
		frmAlimentacion.getContentPane().add(lblSal);

		textFieldSal = new JTextField();
		textFieldSal.setBounds(259, 567, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldSal);
		textFieldSal.setColumns(10);

		JLabel lblCalorias = new JLabel("Calorías:");
		lblCalorias.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCalorias.setForeground(new Color(255, 165, 0));
		lblCalorias.setBounds(100, 592, 70, 15);
		frmAlimentacion.getContentPane().add(lblCalorias);

		textFieldCalorias = new JTextField();
		textFieldCalorias.setBounds(259, 598, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldCalorias);
		textFieldCalorias.setColumns(10);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setVisible(false);
		btnMostrar.setBounds(921, -2, 117, 25);
		frmAlimentacion.getContentPane().add(btnMostrar);

		JComboBox comboBoxAlimentos = new JComboBox();
		comboBoxAlimentos.setBounds(715, 319, 106, 21);
		frmAlimentacion.getContentPane().add(comboBoxAlimentos);

		JLabel lblNewLabel = new JLabel("Alimento:");
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(635, 323, 70, 13);
		frmAlimentacion.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cantidad:");
		lblNewLabel_1.setForeground(new Color(255, 165, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(858, 323, 73, 13);
		frmAlimentacion.getContentPane().add(lblNewLabel_1);

		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(940, 320, 70, 19);
		frmAlimentacion.getContentPane().add(textFieldCantidad);
		textFieldCantidad.setColumns(10);

		JButton btnAñadir = new JButton("Añadir");
		btnAñadir.setBackground(new Color(255, 165, 0));
		btnAñadir.setForeground(new Color(255, 255, 255));
		btnAñadir.setBounds(802, 385, 85, 21);
		frmAlimentacion.getContentPane().add(btnAñadir);

		JButton btnTotalDiario = new JButton("Total Diario");
		btnTotalDiario.setBackground(new Color(255, 165, 0));
		btnTotalDiario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTotalDiario.setForeground(new Color(255, 255, 255));
		btnTotalDiario.setBounds(783, 422, 123, 21);
		frmAlimentacion.getContentPane().add(btnTotalDiario);

		JLabel lblGrasas_1 = new JLabel("Grasas:");
		lblGrasas_1.setForeground(new Color(255, 165, 0));
		lblGrasas_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGrasas_1.setBounds(901, 479, 99, 13);
		frmAlimentacion.getContentPane().add(lblGrasas_1);

		JLabel lblGrasasSaturadas_1 = new JLabel("Grasas Saturadas:");
		lblGrasasSaturadas_1.setForeground(new Color(255, 165, 0));
		lblGrasasSaturadas_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGrasasSaturadas_1.setBounds(1093, 479, 141, 13);
		frmAlimentacion.getContentPane().add(lblGrasasSaturadas_1);

		JLabel lblHidratos_1 = new JLabel("Hidratos:");
		lblHidratos_1.setForeground(new Color(255, 165, 0));
		lblHidratos_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHidratos_1.setBounds(898, 526, 73, 13);
		frmAlimentacion.getContentPane().add(lblHidratos_1);

		JLabel lblAzucares_1 = new JLabel("Azúcares:");
		lblAzucares_1.setForeground(new Color(255, 165, 0));
		lblAzucares_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAzucares_1.setBounds(1093, 530, 99, 13);
		frmAlimentacion.getContentPane().add(lblAzucares_1);

		textFieldGrasasTotales = new JTextField();
		textFieldGrasasTotales.setEditable(false);
		textFieldGrasasTotales.setColumns(10);
		textFieldGrasasTotales.setBounds(981, 477, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldGrasasTotales);

		textFieldGrasasSatTotal = new JTextField();
		textFieldGrasasSatTotal.setEditable(false);
		textFieldGrasasSatTotal.setColumns(10);
		textFieldGrasasSatTotal.setBounds(1230, 476, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldGrasasSatTotal);

		textFieldAzucarTotal = new JTextField();
		textFieldAzucarTotal.setEditable(false);
		textFieldAzucarTotal.setColumns(10);
		textFieldAzucarTotal.setBounds(1230, 526, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldAzucarTotal);

		textFieldHidratosTotal = new JTextField();
		textFieldHidratosTotal.setEditable(false);
		textFieldHidratosTotal.setColumns(10);
		textFieldHidratosTotal.setBounds(981, 528, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldHidratosTotal);

		JLabel lblProtenias_1 = new JLabel("Proteínas:");
		lblProtenias_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProtenias_1.setForeground(new Color(255, 165, 0));
		lblProtenias_1.setBounds(901, 573, 85, 15);
		frmAlimentacion.getContentPane().add(lblProtenias_1);

		textFieldProteinasTotal = new JTextField();
		textFieldProteinasTotal.setEditable(false);
		textFieldProteinasTotal.setColumns(10);
		textFieldProteinasTotal.setBounds(981, 572, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldProteinasTotal);

		JLabel lblSal_1 = new JLabel("Sal:");
		lblSal_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSal_1.setForeground(new Color(255, 165, 0));
		lblSal_1.setBounds(1093, 574, 70, 15);
		frmAlimentacion.getContentPane().add(lblSal_1);

		textFieldSalTotal = new JTextField();
		textFieldSalTotal.setEditable(false);
		textFieldSalTotal.setColumns(10);
		textFieldSalTotal.setBounds(1230, 562, 60, 17);
		frmAlimentacion.getContentPane().add(textFieldSalTotal);

		JLabel lblCalorias_1 = new JLabel("Calorías:");
		lblCalorias_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCalorias_1.setForeground(new Color(255, 165, 0));
		lblCalorias_1.setBounds(996, 635, 70, 15);
		frmAlimentacion.getContentPane().add(lblCalorias_1);

		textFieldCaloriastotal = new JTextField();
		textFieldCaloriastotal.setEditable(false);
		textFieldCaloriastotal.setColumns(10);
		textFieldCaloriastotal.setBounds(1093, 634, 60, 19);
		frmAlimentacion.getContentPane().add(textFieldCaloriastotal);

		JLabel lblG = new JLabel("g");
		lblG.setForeground(new Color(255, 165, 0));
		lblG.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblG.setBounds(1021, 324, 45, 13);
		frmAlimentacion.getContentPane().add(lblG);

		/**
		 * Evento al pulsar el botón mostrar, hace una conexion con la base de datos
		 * para rellenar la tabla de alimentos
		 */
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Alimento> alimentos = new ArrayList<>();

				alimentos = alimentoDAO.selectAllAlimento();

				modelAlimentos.setRowCount(0);
				for (Alimento a : alimentos) {
					Object[] row = new Object[9];
					row[0] = a.getIdAlimento();
					row[1] = a.getNombre();
					row[2] = a.getGrasas();
					row[3] = a.getGrasasSaturadas();
					row[4] = a.getHidratos();
					row[5] = a.getAzucar();
					row[6] = a.getProteinas();
					row[7] = a.getSal();
					row[8] = a.getCalorias();
					modelAlimentos.addRow(row);
				}

			}
		});

		btnMostrar.doClick();

		/**
		 * Evento al clicar sobre la tabla de alimentos, dependiendo la fila que se
		 * seleccione se rellenarán los textFields con unos valores
		 */
		tableAlimentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = tableAlimentos.getSelectedRow();
				TableModel modelAlimentos = tableAlimentos.getModel();
				List<Alimento> alimentos = alimentoDAO.selectAllAlimento();
				for (Alimento a : alimentos) {
					String nombre = a.getNombre();
					comboBoxAlimentos.addItem(nombre);

				}
				textFieldIdAlimento.setText(modelAlimentos.getValueAt(index, 0).toString());
				textFieldNombre.setText(modelAlimentos.getValueAt(index, 1).toString());
				textFieldGrasas.setText(modelAlimentos.getValueAt(index, 2).toString());
				textFieldGrasasSat.setText(modelAlimentos.getValueAt(index, 3).toString());
				textFieldHidratos.setText(modelAlimentos.getValueAt(index, 4).toString());
				textFieldAzucar.setText(modelAlimentos.getValueAt(index, 5).toString());
				textFieldProteinas.setText(modelAlimentos.getValueAt(index, 6).toString());
				textFieldSal.setText(modelAlimentos.getValueAt(index, 7).toString());
				textFieldCalorias.setText(modelAlimentos.getValueAt(index, 8).toString());
				comboBoxAlimentos.setSelectedItem(modelAlimentos.getValueAt(index, 1).toString());

				String combo = modelAlimentos.getValueAt(index, 1).toString();
				comboBoxAlimentos.setSelectedItem(combo);
			}
		});

		/**
		 * Evento al pulsar el botón crear alimento, crea un alimento y lo inserta en la
		 * base de datos. Hace el control de errores necesario.
		 */
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String mensaje = "";
					boolean correcto = true;

					if (!comprobarExpReg(textFieldNombre.getText(), erNombre) || textFieldNombre.getText().isEmpty()) {
						mensaje += "Nombre no válido\n";
						correcto = false;
					}
					if (comprobarExpReg(textFieldGrasas.getText(), erNombre) || textFieldGrasas.getText().isEmpty()) {
						mensaje += "Grasas no válidas\n";
						correcto = false;
					}
					if (comprobarExpReg(textFieldGrasasSat.getText(), erNombre)
							|| textFieldGrasasSat.getText().isEmpty()) {
						mensaje += "Grasas saturadas no válidas\n";
						correcto = false;
					}
					if (comprobarExpReg(textFieldHidratos.getText(), erNombre)
							|| textFieldHidratos.getText().isEmpty()) {
						mensaje += "Hidratos no válidos\n";
						correcto = false;
					}
					if (comprobarExpReg(textFieldAzucar.getText(), erNombre) || textFieldAzucar.getText().isEmpty()) {
						mensaje += "Azucar no válido\n";
						correcto = false;
					}
					if (comprobarExpReg(textFieldProteinas.getText(), erNombre)
							|| textFieldProteinas.getText().isEmpty()) {
						mensaje += "Proteinas no válidas\n";
						correcto = false;
					}
					if (comprobarExpReg(textFieldSal.getText(), erNombre) || textFieldSal.getText().isEmpty()) {
						mensaje += "Sal no válida\n";
						correcto = false;
					}
					if (comprobarExpReg(textFieldCalorias.getText(), erNombre)
							|| textFieldCalorias.getText().isEmpty()) {
						mensaje += "Calorías no válidas\n";
						correcto = false;
					}

					if (correcto) {
						String nombre = textFieldNombre.getText();
						double grasas = Double.parseDouble(textFieldGrasas.getText());
						double grasasSat = Double.parseDouble(textFieldGrasasSat.getText());
						double hidratos = Double.parseDouble(textFieldHidratos.getText());
						double azucar = Double.parseDouble(textFieldAzucar.getText());
						double proteinas = Double.parseDouble(textFieldProteinas.getText());
						double sal = Double.parseDouble(textFieldSal.getText());
						double calorias = Double.parseDouble(textFieldCalorias.getText());

						if ((grasasSat > grasas) && (azucar > hidratos)) {
							mensaje = "Las grasas saturadas deben ser menores a las grasas y el azúcar debe ser menor a los hidratos";
						} else if (azucar > hidratos) {
							mensaje = "El azúcar debe ser menor a los hidratos";
						} else if (grasasSat > grasas) {
							mensaje = "Las grasas saturadas deben ser menores a las grasas";
						} else {
							Alimento a = new Alimento(nombre, grasas, grasasSat, hidratos, azucar, proteinas, sal,
									calorias);
							alimentoDAO.insertAlimento(a);
							textFieldIdAlimento.setText("");
							textFieldNombre.setText("");
							textFieldGrasas.setText("");
							textFieldGrasasSat.setText("");
							textFieldHidratos.setText("");
							textFieldAzucar.setText("");
							textFieldProteinas.setText("");
							textFieldSal.setText("");
							textFieldCalorias.setText("");
							mensaje = "Alimento creado";
							btnMostrar.doClick();
						}

					}
					JOptionPane.showMessageDialog(null, mensaje);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
				}

			}
		});

		/**
		 * Evento al pulsar el botón actualizar alimento, se debe seleccinar un alimento
		 * de la tabla cambiar los valores deseados. Una vez hecho eso se actualizará la
		 * tabla.
		 */
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					if (!textFieldIdAlimento.getText().isEmpty()) {
						String mensaje = "";
						boolean correcto = true;

						if (!comprobarExpReg(textFieldNombre.getText(), erNombre) || textFieldNombre.getText().isEmpty()) {
							mensaje += "Nombre no válido\n";
							correcto = false;
						}
						if (comprobarExpReg(textFieldGrasas.getText(), erNombre) || textFieldGrasas.getText().isEmpty()) {
							mensaje += "Grasas no válidas\n";
							correcto = false;
						}
						if (comprobarExpReg(textFieldGrasasSat.getText(), erNombre) || textFieldGrasasSat.getText().isEmpty()) {
							mensaje += "Grasas saturadas no válidas\n";
							correcto = false;
						}
						if (comprobarExpReg(textFieldHidratos.getText(), erNombre) || textFieldHidratos.getText().isEmpty()) {
							mensaje += "Hidratos no válidos\n";
							correcto = false;
						}
						if (comprobarExpReg(textFieldAzucar.getText(), erNombre) || textFieldAzucar.getText().isEmpty()) {
							mensaje += "Azucar no válido\n";
							correcto = false;
						}
						if (comprobarExpReg(textFieldProteinas.getText(), erNombre) || textFieldProteinas.getText().isEmpty()) {
							mensaje += "Proteinas no válidas\n";
							correcto = false;
						}
						if (comprobarExpReg(textFieldSal.getText(), erNombre) || textFieldSal.getText().isEmpty()) {
							mensaje += "Sal no válida\n";
							correcto = false;
						}
						if (comprobarExpReg(textFieldCalorias.getText(), erNombre) || textFieldCalorias.getText().isEmpty()) {
							mensaje += "Calorías no válidas\n";
							correcto = false;
						}

						if (correcto) {
							int id = Integer.parseInt(textFieldIdAlimento.getText());
							String nombre = textFieldNombre.getText();
							double grasas = Double.parseDouble(textFieldGrasas.getText());
							double grasasSat = Double.parseDouble(textFieldGrasasSat.getText());
							double hidratos = Double.parseDouble(textFieldHidratos.getText());
							double azucar = Double.parseDouble(textFieldAzucar.getText());
							double proteinas = Double.parseDouble(textFieldProteinas.getText());
							double sal = Double.parseDouble(textFieldSal.getText());
							double calorias = Double.parseDouble(textFieldCalorias.getText());

							Alimento a = alimentoDAO.selectAlimentoById(id);
							a.setNombre(nombre);
							a.setGrasas(grasas);
							a.setGrasasSaturadas(grasasSat);
							a.setHidratos(hidratos);
							a.setAzucar(azucar);
							a.setProteinas(proteinas);
							a.setSal(sal);
							a.setCalorias(calorias);

							alimentoDAO.updateAlimento(a);
							textFieldIdAlimento.setText("");
							textFieldNombre.setText("");
							textFieldGrasas.setText("");
							textFieldGrasasSat.setText("");
							textFieldHidratos.setText("");
							textFieldAzucar.setText("");
							textFieldProteinas.setText("");
							textFieldSal.setText("");
							textFieldCalorias.setText("");
							mensaje = "Alimento actualizado";
							btnMostrar.doClick();
						}
						JOptionPane.showMessageDialog(null, mensaje);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un alimento");
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Formato de datos incorrecto");
				}
			}
		});

		/**
		 * Evento al pulsar el botón borrar alimento, se debe seleccionar el alimento
		 * que se desea eliminar y lo elimina de la base de datos y de la tabla.
		 */
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(textFieldIdAlimento.getText());
					alimentoDAO.deleteAlimento(id);
					textFieldIdAlimento.setText("");
					textFieldNombre.setText("");
					textFieldGrasas.setText("");
					textFieldGrasasSat.setText("");
					textFieldHidratos.setText("");
					textFieldAzucar.setText("");
					textFieldProteinas.setText("");
					textFieldSal.setText("");
					textFieldCalorias.setText("");
					btnMostrar.doClick();
					JOptionPane.showMessageDialog(null, "Alimento borrado");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Debes escoger un alimento");
				}
			}
		});

		/**
		 * Evento al pulsar el botón limpiar, lo que hace es vaciar todos los textFields
		 * de la creación de alimentos.
		 */
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textFieldIdAlimento.setText("");
				textFieldNombre.setText("");
				textFieldGrasas.setText("");
				textFieldGrasasSat.setText("");
				textFieldHidratos.setText("");
				textFieldAzucar.setText("");
				textFieldProteinas.setText("");
				textFieldSal.setText("");
				textFieldCalorias.setText("");
			}
		});

		/**
		 * Evento al pulsar el comboBox de alimentos, hace una consulta en la base de
		 * datos y coge el nombre de todos los alimentos para insertarlos en el
		 * comboBox.
		 */
		comboBoxAlimentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				comboBoxAlimentos.removeAllItems();

				List<Alimento> alimentos = alimentoDAO.selectAllAlimento();
				for (Alimento a : alimentos) {
					String nombre = a.getNombre();
					comboBoxAlimentos.addItem(nombre);

				}

			}
		});

		/**
		 * Al pulsar el botón añadir se activa el evento lo que hace es coger el nombre
		 * del alimento que está en el comboBox y la cantidad que se ha ingerido de
		 * dicho alimento y almacenarlos para, posteriormente, realizar las operaciones
		 * necesarias.
		 */
		btnAñadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				
						if (!textFieldCantidad.getText().isEmpty()) {

							String alimento = comboBoxAlimentos.getSelectedItem().toString();
							double cantidad = Double.parseDouble(textFieldCantidad.getText());
							Alimento a = alimentoDAO.selectAlimentoByNombre(alimento);

							
							grasas += calcularCantidad(a.getGrasas(), cantidad);
							grasasSat += calcularCantidad(a.getGrasasSaturadas(), cantidad);
							hidratos += calcularCantidad(a.getHidratos(), cantidad);
							azucar += calcularCantidad(a.getAzucar(), cantidad);
							proteinas += calcularCantidad(a.getProteinas(), cantidad);
							sal += calcularCantidad(a.getSal(), cantidad);
							calorias += calcularCantidad(a.getCalorias(), cantidad);

							if (alimentosComidos.containsKey(alimento)) {
								cantidad = alimentosComidos.get(alimento) + cantidad;
								alimentosComidos.remove(alimento);
							}
							alimentosComidos.put(alimento, cantidad);

							JOptionPane.showMessageDialog(null, "Alimento añadido");

							comboBoxAlimentos.setSelectedIndex(-1);
							textFieldCantidad.setText("");
							
						} else {
							JOptionPane.showMessageDialog(null, "Introduce la cantidad de alimento");
						}

					

				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Selecciona un alimento");
				} catch (IllegalArgumentException ex2) {
					JOptionPane.showMessageDialog(null, "Formato de cantidad no válido");
				}

			}
		});

		/**
		 * Al pulsar el botón total diario se hacen una serie de comprobaciones para
		 * saber si se ha pasado en algún macronutriente.
		 */
		btnTotalDiario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textFieldGrasasTotales.setText(String.format("%.2f", grasas));

				if ((grasasSat * 9) > MAX_KCAL) {
					textFieldGrasasSatTotal.setForeground(Color.RED);
				}
				textFieldGrasasSatTotal.setText(String.format("%.2f", grasasSat));

				textFieldHidratosTotal.setText(String.format("%.2f", hidratos));

				if (azucar > MAX_AZUCAR) {
					textFieldAzucarTotal.setForeground(Color.RED);
				}
				textFieldAzucarTotal.setText(String.format("%.2f", azucar));

				textFieldProteinasTotal.setText(String.format("%.2f", proteinas));

				if (sal > MAX_SAL) {
					textFieldSalTotal.setForeground(Color.RED);
				}
				textFieldSalTotal.setText(String.format("%.2f", sal));

				textFieldCaloriastotal.setText(String.format("%.2f", calorias));

				modelAlimentosComidos.setRowCount(0);
				for (String a : alimentosComidos.keySet()) {
					Object[] row = new Object[2];
					row[0] = a;
					row[1] = alimentosComidos.get(a);
					modelAlimentosComidos.addRow(row);
				}

			}
		});
	}
}
