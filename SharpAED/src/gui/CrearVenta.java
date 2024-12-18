package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arreglos.ArregloClientes;
import arreglos.ArregloProductos;
import arreglos.ArregloVentas;
import clases.Cliente;
import clases.Producto;
import clases.Venta;
import utilidades.Validacion;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import java.awt.Cursor;

public class CrearVenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox<Producto> cboProductos;
	private JComboBox<Cliente> cboClientes;
	private JLabel txtPrecio;
	private JLabel lblNewLabel_3;
	private JTextField txtIGV;
	private JLabel lblNewLabel_5;
	private JTextField txtCantidad;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnCrearVenta;
	private JButton btnCerrar;

	private String cantidad = "0";
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JPanel panel_1;
	private JLabel lblNewLabel_7;
	private JLabel txtCliente;
	private JLabel lblNewLabel_9;
	private JLabel txtCodigoCliente;
	private JLabel lblNewLabel_11;
	private JLabel txtDni;
	private JLabel lblNewLabel_13;
	private JLabel txtDireccion;
	private JLabel lblNewLabel_15;
	private JLabel txtFecha;
	private JPanel panel_2;
	private JLabel txtStockActual;
	private JPanel panel_3;

	private LocalDateTime fecha = LocalDateTime.now();
	private String fechaString = fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();

	/**
	 * Launch the application.
	 */

	public static void abrirVentana() {
		try {
			CrearVenta dialog = new CrearVenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearVenta() {
		setResizable(false);
		setTitle("Crear Venta");
		setBounds(100, 100, 645, 714);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 410, 535, 214);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Cantidad", "Precio Unitario", "Subtotal", "IGV", "Total" }));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		panel_3 = new JPanel();
		panel_3.setBounds(43, 11, 542, 214);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 352, 214);
		panel_3.add(panel);
		panel.setLayout(new GridLayout(0, 2, 6, 12));

		lblNewLabel = new JLabel("Producto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel);

		cboProductos = new JComboBox();
		cboProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedCboProductos(e);
			}
		});
		panel.add(cboProductos);

		lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel_1);

		cboClientes = new JComboBox<Cliente>();
		panel.add(cboClientes);

		lblNewLabel_5 = new JLabel("Cantidad");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel_5);

		txtCantidad = new JTextField();

		panel.add(txtCantidad);
		txtCantidad.setColumns(10);

		lblNewLabel_4 = new JLabel("Stock Actual");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel_4);

		txtStockActual = new JLabel("0");
		panel.add(txtStockActual);

		lblNewLabel_2 = new JLabel("Precio unitario");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel_2);

		txtPrecio = new JLabel("S/ 0.00");
		panel.add(txtPrecio);

		lblNewLabel_3 = new JLabel("IGV (%)");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel_3);

		txtIGV = new JTextField();
		txtIGV.setText("3");
		txtIGV.setEditable(false);
		panel.add(txtIGV);
		txtIGV.setColumns(10);

		btnCrearVenta = new JButton("Crear");
		btnCrearVenta.setBounds(401, 0, 141, 23);
		panel_3.add(btnCrearVenta);

		btnCerrar = new JButton("Cancelar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnCerrar(e);
			}
		});
		btnCerrar.setBounds(401, 34, 141, 23);
		panel_3.add(btnCerrar);
		btnCrearVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnCrearVenta(e);
			}
		});
		for (Producto producto : ArregloProductos.getProductos()) {
			cboProductos.addItem(producto);
		}
		for (Cliente cliente : ArregloClientes.getClientes()) {
			cboClientes.addItem(cliente);
		}

		lblNewLabel_6 = new JLabel("BOLETA DE VENTA");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(250, 251, 128, 19);
		contentPanel.add(lblNewLabel_6);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 276, 609, 133);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBounds(36, 11, 536, 88);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_7 = new JLabel("Cliente: ");
		lblNewLabel_7.setBounds(0, 0, 56, 14);
		panel_2.add(lblNewLabel_7);

		txtCliente = new JLabel("");
		txtCliente.setBounds(58, 0, 122, 14);
		panel_2.add(txtCliente);
		txtCliente.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		lblNewLabel_9 = new JLabel("Codigo de cliente: ");
		lblNewLabel_9.setBounds(0, 36, 116, 14);
		panel_2.add(lblNewLabel_9);

		txtCodigoCliente = new JLabel("");
		txtCodigoCliente.setBounds(104, 36, 94, 14);
		panel_2.add(txtCodigoCliente);

		lblNewLabel_11 = new JLabel("DNI: ");
		lblNewLabel_11.setBounds(0, 74, 25, 14);
		panel_2.add(lblNewLabel_11);

		txtDni = new JLabel("");
		txtDni.setBounds(35, 74, 116, 14);
		panel_2.add(txtDni);

		lblNewLabel_13 = new JLabel("Direccion: ");
		lblNewLabel_13.setBounds(337, 0, 89, 14);
		panel_2.add(lblNewLabel_13);

		txtDireccion = new JLabel("");
		txtDireccion.setBounds(414, 0, 122, 14);
		panel_2.add(txtDireccion);

		lblNewLabel_15 = new JLabel("Fecha: ");
		lblNewLabel_15.setBounds(337, 36, 89, 14);
		panel_2.add(lblNewLabel_15);

		txtFecha = new JLabel(fechaString);
		txtFecha.setBounds(390, 36, 107, 14);
		panel_2.add(txtFecha);
	}

	protected void actionPerformedCboProductos(ActionEvent e) {
		mostrarDescripcionProducto();
	}

	protected void actionPerformedBtnCrearVenta(ActionEvent e) {
		crearVenta();

	}

	protected Cliente obtenerClienteSeleccionado() {
		return (Cliente) cboClientes.getSelectedItem();
	}

	protected Producto obtenerProductoSeleccionado() {
		return (Producto) cboProductos.getSelectedItem();
	}

//	Crear la venta y actualizar el stock del producto
	protected void crearVenta() {
		Producto producto = obtenerProductoSeleccionado();
		boolean cantidadValida = Validacion.validarInteger(txtCantidad.getText());
		boolean stockValido = validarStock();
		Cliente cliente = obtenerClienteSeleccionado();
		if (!cantidadValida) {
			JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válida.");
			return;
		}

		if (stockValido) {
			ArregloVentas.escribirVenta(new Venta(cliente.getCodigoCliente(),
					obtenerProductoSeleccionado().getCodigoProducto(), Integer.parseInt(txtCantidad.getText()),
					calcularSubtotal(), calcularIGV(), calcularTotal(), "20/02/2001"));
			actualizarStock();
			txtStockActual
					.setText(Integer.parseInt(txtStockActual.getText()) - Integer.parseInt(txtCantidad.getText()) + "");

			listarTablaProductos();
			imprimirDetallesCliente(cliente);

//			producto.setTotalAcumulado(producto.getTotalAcumulado() + calcularTotal());
			ArregloProductos.modificarProducto(cboProductos.getSelectedIndex(),
					new Producto(producto.getNombre(), producto.getPrecio(), producto.getStockActual(),
							producto.getStockMinimo(), producto.getStockMaximo(),
							producto.getTotalAcumulado() + calcularTotal(),
							producto.getCantidadVendida() + Integer.parseInt(txtCantidad.getText())));
			ArregloProductos.actualizarProductos();

			JOptionPane.showMessageDialog(null, "Venta creada correctamente.");
		}
		;
	}

	protected boolean validarStock() {
		Producto producto = obtenerProductoSeleccionado();
		int stock = producto.getStockActual();
		int cantidad = Integer.parseInt(txtCantidad.getText());

		if (cantidad > stock) {
			JOptionPane.showMessageDialog(null, "No hay suficiente stock.");
			txtCantidad.setText("0");
			return false;
		}
		return true;
	}

	protected void actualizarStock() {
		Producto producto = obtenerProductoSeleccionado();
		int pos = cboProductos.getSelectedIndex();
		int stock = producto.getStockActual();
		int cantidad = Integer.parseInt(txtCantidad.getText());

		ArregloProductos.modificarProducto(pos, new Producto(producto.getNombre(), producto.getPrecio(),
				stock - cantidad, producto.getStockMinimo(), producto.getStockMaximo(), producto.getTotalAcumulado(), producto.getCantidadVendida() + cantidad));
		ArregloProductos.actualizarProductos();
	}

//	Mostrar la descripcion del producto en una tabla
	protected void listarTablaProductos() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		if (table.getRowCount() > 0) {
			model.setRowCount(0);
		}

		Producto prod = obtenerProductoSeleccionado();
		int cantidad = Integer.parseInt(txtCantidad.getText());
		double precio = prod.getPrecio();
		double subtotal = calcularSubtotal();
		double igv = Double.parseDouble(txtIGV.getText());
		double igvTotal = calcularIGV();
		double total = subtotal + igvTotal;

		model.addRow(new Object[] { prod.getCodigoProducto(), prod.getNombre(), cantidad, precio, subtotal, igvTotal,
				total });

	}

//	Mostrar el precio y el stock actual del producto seleccionado en los campos de texto
	protected void mostrarDescripcionProducto() {
		Producto producto = obtenerProductoSeleccionado();
		txtPrecio.setText("S/ " + producto.getPrecio());
		txtStockActual.setText(String.valueOf(producto.getStockActual()));
	}

	protected void imprimirDetallesCliente(Cliente cliente) {
		txtCliente.setText(cliente.getNombres() + " " + cliente.getApellidos());
		txtCodigoCliente.setText(String.valueOf(cliente.getCodigoCliente()));
		txtDni.setText(cliente.getDni());
		txtDireccion.setText(cliente.getDireccion());

	}

	protected double calcularSubtotal() {
		Producto producto = obtenerProductoSeleccionado();
		int cantidad = Integer.parseInt(txtCantidad.getText());
		double precio = producto.getPrecio();
		double subtotal = cantidad * precio;
//		String subtotalString = String.format("%.2f", subtotal);
		return subtotal;
	}

	private double calcularIGV() {
		double subtotal = calcularSubtotal();
		double igv = Double.parseDouble(txtIGV.getText());
		double igvTotal = subtotal * (igv / 100);
//		String igvString = String.format("%.2f", igvTotal);
		return igvTotal;
	}

	private double calcularTotal() {
		Producto producto = obtenerProductoSeleccionado();
		double subtotal = calcularSubtotal();
		double igv = calcularIGV();
		double total = subtotal + igv;
//		String totalString = String.format("%.2f", total);

		return total;

	}

	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose();
	}
}
