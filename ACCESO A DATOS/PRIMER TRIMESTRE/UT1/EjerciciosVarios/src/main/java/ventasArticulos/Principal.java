package ventasArticulos;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



public class Principal {

	public static void main(String[] args) {
		leerXML();
	}
	private static void leerXML() {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(ventasarticulos.class);
			Unmarshaller unmars = context.createUnmarshaller();
			ventasarticulos objeto = (ventasarticulos) unmars.unmarshal(new File("ventasarticulos.xml"));
			// recuperar los datos de las librerias
			Articulo articulo = objeto.getArticulo();
			ArrayList<Venta> ventas = objeto.getListaVentas();
			System.out.println("Código: "+articulo.getCodigo()+", nombre: "+articulo.getDenominacion()+", stock: "+articulo.getStock()+", precio: "+articulo.getPrecio());
			System.out.printf("%8s %11s %25s %8s %8s %n","NUMVENTA","FECHA VENTA","NOM-CLIENTE","UNIDADES","IMPORTE");
			System.out.printf("%8s %11s %25s %8s %8s %n","--------","-----------","-------------------------","--------","--------");
			float sumaimpor=0f;
			int sumauni=0;
				for(Venta vent: ventas) {
				float imp=vent.getUnidades()*articulo.getPrecio();
				System.out.printf("%8s %11s %25s %8s %8s %n",vent.getNumVenta(),vent.getFecha(),vent.getCliente(),vent.getUnidades(),imp);
				sumauni+=vent.getUnidades();
				sumaimpor+=imp;
					//System.out.println("Número de venta: "+vent.getNumVenta()+", unidades: "+vent.getUnidades()+", cliente: "+vent.getCliente()+", fecha: "+vent.getFecha());
				}
				System.out.printf("%8s %11s %25s %8s %8s %n","--------","-----------","-------------------------","--------","--------");
				System.out.printf("%8s %11s %25s %8s %8s %n","TOTALES:","","",sumauni,sumaimpor);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
