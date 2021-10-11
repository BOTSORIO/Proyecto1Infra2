package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LeerArchivo {

	public  static ArrayList<String> leerArchivo(String nombre) {

		File archivo = null;
		FileReader fileReader = null;
		BufferedReader buffer = null;
		ArrayList<String> lineas= new ArrayList<>();
		
		try {

			archivo = new File(nombre + ".txt");
			fileReader = new FileReader(archivo);
			buffer = new BufferedReader(fileReader);

			String linea;

			while ((linea = buffer.readLine()) != null)
				
				lineas.add(linea+"-");
			   
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != fileReader) {
					fileReader.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return lineas;
	}

}
