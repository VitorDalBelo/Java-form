package validation;

import javax.swing.JOptionPane;

public class FieldsValidation {
	public static boolean fieldRequired(String fieldValue) {
		return 	fieldValue.isBlank();
	}
	public static boolean scoreValidation(String fieldValue) {
		Double score = Double.parseDouble(fieldValue);
		return !(score <= 10 && score >= 0);
	}
	
	public static boolean absentValidation(String fieldValue) {
		Integer absent = Integer.parseInt(fieldValue);
		return !(absent >= 0);
	}
	public static boolean validateAll(String nome, String disciplina, String faltas , String nota1, String nota2) {
		try {
			if(fieldRequired(nome)) throw new Exception("O campo nome é obrigatório");
			if(fieldRequired(disciplina)) throw new Exception("O campo disciplina é obrigatório");
			if(fieldRequired(faltas)) throw new Exception("O campo faltas é obrigatório");
			else if(absentValidation(faltas)) throw new Exception("O campo faltas dete conter um número inteiro maior ou igual a 0.");
			if(fieldRequired(nota1)) throw new Exception("O campo nota1 é obrigatório");
			else if(scoreValidation(nota1)) throw new Exception("O valor de nota1 deve estar entre 0 e 10.");
			if(fieldRequired(nota2)) throw new Exception("O campo nota2 é obrigatório");
			else if(scoreValidation(nota2)) throw new Exception("O valor de nota2 deve estar entre 0 e 10.");
			return true;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return false;
		}
	}

}
