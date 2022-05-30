package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import validation.FieldsValidation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class controleForm extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField faltasField;
	private JTextField nota1Field;
	private JTextField nota2Field;
	private JTable table;
	private DefaultTableModel model;
	private Integer selectedRow=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					controleForm frame = new controleForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public controleForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNota = new JLabel("Nota 1");
		lblNota.setBounds(12, 74, 70, 15);
		contentPane.add(lblNota);
		
		JLabel lblNota_1 = new JLabel("Nota 2");
		lblNota_1.setBounds(12, 93, 70, 15);
		contentPane.add(lblNota_1);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 14, 70, 15);
		contentPane.add(lblNome);
		
		nameField = new JTextField();
		nameField.setBounds(167, 12, 114, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		
		JLabel lblDisciplinaDoAluno = new JLabel("Disciplina do Aluno");
		lblDisciplinaDoAluno.setBounds(12, 34, 137, 15);
		contentPane.add(lblDisciplinaDoAluno);
		ArrayList<String> disciplinas = new ArrayList<String>();
		disciplinas.addAll(Arrays.asList("","ADM","Markenting","Ciência da computação","Matemática"));
		JComboBox disciplina = new JComboBox(disciplinas.toArray());
		disciplina.addActionListener(new ActionListener() {     
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String v = disciplina.getSelectedItem().toString();
			}
		   });
		disciplina.setBounds(167, 29, 123, 24);
		contentPane.add(disciplina);
		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setBounds(12, 55, 70, 15);
		contentPane.add(lblFaltas);
		faltasField = new JTextField();
		faltasField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) e.consume();
		        
			}
		});
		
		faltasField.setBounds(167, 59, 114, 19);
		contentPane.add(faltasField);
		faltasField.setColumns(10);
		
		nota1Field = new JTextField();
		nota1Field.setText("");
		nota1Field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String fieldText = nota1Field.getText();
				char c = e.getKeyChar();
				if(((c < '0') || (c > '9')  )  && (c!='.' ||  fieldText.isBlank() || fieldText.contains(".")))e.consume();
			}
		});
		nota1Field.setBounds(167, 91, 114, 19);
		contentPane.add(nota1Field);
		nota1Field.setColumns(10);
		
		nota2Field = new JTextField();
		nota2Field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String fieldText = nota2Field.getText();
				char c = e.getKeyChar();
				if(((c < '0') || (c > '9')  )  && (c!='.' ||  fieldText.isBlank() || fieldText.contains(".")))e.consume();
			}
		});
		nota2Field.setBounds(176, 115, 114, 19);
		contentPane.add(nota2Field);
		nota2Field.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(302, 14, 383, 204);
		contentPane.add(scrollPane);
		
		table = new JTable(){
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	       };
		model = new DefaultTableModel();
		Object[] columns = {"nome","disciplina","faltas","nota1","nota2"};
		Object[] row = new Object[5];
		table.setModel(model);
		model.setColumnIdentifiers(columns);

	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
    	
	        Object[] selectedData = new Object[columns.length] ;

	        selectedRow = table.getSelectedRow();
	        for(int i=0 ;i<columns.length;i++) {
	        	selectedData[i]=table.getValueAt(selectedRow, i);
	        }
	        
	        
			nameField.setText((String) selectedData[0]);
			disciplina.setSelectedIndex(disciplinas.indexOf(selectedData[1]));
			faltasField.setText((String) selectedData[2]);
			nota1Field.setText((String) selectedData[3]);
			nota2Field.setText((String) selectedData[4]);
    	
	        
	      }

	    });
		scrollPane.setViewportView(table);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = nameField.getText();
				String disciplinaItem = disciplina.getSelectedItem().toString();
				String faltas =faltasField.getText();
				String nota1 = nota1Field.getText();
				String nota2 = nota2Field.getText();
				if(FieldsValidation.validateAll(nome, disciplinaItem, faltas, nota1, nota2)) {
					row[0]= nome;
					row[1]= disciplinaItem;
					row[2]= faltas;
					row[3]=nota1;
					row[4]= nota2;
					model.addRow(row);
					nameField.setText("");
					disciplina.setSelectedIndex(0);
					faltasField.setText("");
					nota1Field.setText("");
					nota2Field.setText("");					
				}

				
			}
		});
		btnInserir.setBounds(32, 153, 117, 25);
		contentPane.add(btnInserir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedRow!=null) {
					model.removeRow(table.getSelectedRow());
					selectedRow=null;
				}
			}
		});
		btnExcluir.setBounds(32, 190, 117, 25);
		contentPane.add(btnExcluir);
			


		

	}
}
