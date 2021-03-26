import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import herencia.BotonSubmit;
import herencia.EjemploFinal;
import herencia.EventoRaton;
import herencia.HerenciaAbstract;
import herencia.PrimerBoton;
import herencia.SegundoBoton;

public class Main {

	public static void main(String[] args) {
		
		 Vector objects=new Vector(20);
		 
		

	    objects.add("333");
		objects.add(Integer.valueOf(0));
		objects.add(Stream.of(Collections.singleton("5")));
		
		for(Object object : objects) {
			System.out.println(object);
		}
		
	}

}
