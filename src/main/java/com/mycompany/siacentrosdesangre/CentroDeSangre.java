package com.mycompany.siacentrosdesangre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CentroDeSangre {
    private String nombre;
    private ArrayList<Campania> campanias;
    private HashMap<String, Integer> stockSangre;
    
    public CentroDeSangre(){
        this.campanias = new ArrayList<>();
        this.stockSangre = new HashMap<>();
    }
    
    public CentroDeSangre(String nombre){
        this.nombre = nombre;
        this.campanias = new ArrayList<>();
        this.stockSangre = new HashMap<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarCampania(Campania nuevo) {
        campanias.add(nuevo);
    }
    
    public void agregarStockSangre(String nombre) {
        stockSangre.put(nombre, 0);
    }

    public void agregarStockSangre(String nombre, int cantidad) {
        stockSangre.put(nombre.toUpperCase(), (stockSangre.get(nombre.toUpperCase()) + cantidad));
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Integer getStockSangre(String clave) {
        if(stockSangre.containsKey(clave) == true)
            return stockSangre.get(clave);
        else
            return null;
    }
    
    /*Metodo agregarSangre: Encargado de dar la opcion al usuario para
    agregar manualmente bolsas de sangre al inventario.*/
    public void agregarSangre()throws IOException{
        int nSangres;
        String tipoSangre;
        tipoSangre = Herramientas.lector("Ingrese el tipo de sangre: ");
        while(!stockSangre.containsKey(tipoSangre.toUpperCase()))
            tipoSangre = Herramientas.lector("El tipo de sangre no existe, ingrese un tipo correcto: ");
        
        nSangres = Integer.parseInt(Herramientas.lector("Ingrese la cantidad de bolsas de sangre: "));
        if(nSangres <= 0)
            System.out.println("El valor ingresado es invalido.");
        else
            this.agregarStockSangre(tipoSangre, nSangres);
         
    }

    /*Metodo buscarCampania: Encargado de buscar una campania
    con cierto id, dentro del ArrayList de campanias. Si la encuentra,
    retorna una referencia a esa campania, sino, un null.*/
    public Campania buscarCampania(int id) {
        int i;
        for(i = 0; i < campanias.size(); i ++){
            if(id == campanias.get(i).getId())
                return campanias.get(i);
        }
        return null;
    }
    
    /*Metodo mostrarCampanias: Encargada de mostrar por consola todas
    las campanias existentes en el ArrayList de campanias.*/
    public void mostrarCampanias(){
        Campania aux;
        String localCamp;
        int x, idCamp;
        if(campanias.isEmpty())
            System.out.println("No existen campanias en el centro de sangre.");
        else
            System.out.println("\nEl centro " + nombre + " tiene campanias en : ");
        
        for(x = 0; x < campanias.size() ; x++){
            aux = campanias.get(x);
            idCamp = aux.getId();
            localCamp = aux.getLocalidad();
            System.out.println("Campania en "+localCamp + "   -  ID "+ idCamp);
        }
    }
    
    /**/
    /*public String []conseguirNombresCampanias(){
        String localidades[] = new String[campanias.size()];
        int i;
        
        for (i = 0; i < campanias.size(); i ++){
            localidades[i] = campanias.get(i).getLocalidad();
        }
        return localidades;
    }*/
    /*public  CentroDeSangre crearCentroSangre() throws IOException{
        CentroDeSangre centro = new CentroDeSangre(Herramientas.lector("Ingrese nombre del nuevo centro de sangre : "));
        return centro;
    }*/
    
    /*Metodo crearStockSangre: Encargado de procesar los datos del
    archivo tiposDeSangre para guardar cada tipo como una clave
    del HashMap stockSangre.*/
    public void crearStockSangre() throws IOException{
        int x;
        ArrayList<String> datosArchivo = Herramientas.leerArchivo("tiposDeSangre.txt");
        for(x = 0; x < datosArchivo.size() ; x ++){
            this.agregarStockSangre(datosArchivo.get(x));
        }
    }
    
    /*Metodo mostrarStockSangre: Encargado de mostrar los tipos de 
    sangre y la cantidad respectiva de bolsas disponibles para ese
    tipo.*/
    public void mostrarStockSangre(CentroDeSangre centro) throws IOException{
        int i;
        ArrayList<String> datosArchivo = Herramientas.leerArchivo("tiposDeSangre.txt");
        String sangre;
        System.out.println("Cantidad de bolsas de sangre disponible por tipo:");
        for(i= 0; i < datosArchivo.size() ; i ++){
            sangre = datosArchivo.get(i);
            System.out.println(sangre + " " + this.getStockSangre(sangre));
        }
    }
    
    /*Metodo mostrarDonacionesDeCampanias: Encargado de mostrar todas las
    donaciones existentes, campania por campania.*/
    public void mostrarDonacionesDeCampanias(){
        int x;
        Campania aux;
        if(campanias.isEmpty())
            System.out.println("No existen campanias en el centro de sangre.");
        else
            System.out.println("Donaciones por campania en centro "+nombre+".");
        
        for(x = 0; x < campanias.size() ; x++){
            aux = campanias.get(x);
            aux.mostrarDonaciones();
        }
    }
    
    /*Metodo crearDonacion: Encargado de crear una nueva donacion a traves
    de datos solicitados al usuario por consola. Tras tomar todos los datos,
    retorna un objeto de clase Donacion con sus atributos rellenos.*/
    public Donacion crearDonacion(int idDonacion) throws IOException{
        String fecha = Herramientas.lector("Ingrese fecha de la donacion(Formato DD/MM/AAAA): ");
        String rut = Herramientas.lector("Ingrese RUT del donante: ");
        String nombre = Herramientas.lector("Ingrese el nombre del donante: ");
        int edad = Integer.parseInt(Herramientas.lector("Ingrese la edad del donante: "));
        String tipoSangre = Herramientas.lector("Ingrese el tipo de sangre del donante: ");
        while(!stockSangre.containsKey(tipoSangre.toUpperCase()))
            tipoSangre = Herramientas.lector("El tipo de sangre no existe, ingrese un tipo correcto: ");
        
        String numeroTelefonico = Herramientas.lector("Ingrese el numero de telefono del donante: ");
        
        Donante persona = new Donante(rut, nombre, edad, tipoSangre, numeroTelefonico);
        
        Donacion donacion = new Donacion(idDonacion, fecha, persona);
        return donacion;
    }
    
    /*Metodo agregarDonacion: Encargado de ofrecer la opcion al usuario
    para agregar una nueva donacion a una campania en especifico. Si
    la campania existe, y la donacion presenta un id nuevo, se
    procedera a crear el objeto clase Donacion. Finalmente, la donacion
    se agrega en la campania correspondiente, ademas de guardar el nuevo
    registro en el documento de datos.*/
    public void agregarDonacion() throws IOException{
        String idLeida = Herramientas.lector("Ingrese la ID de la campania(Numerica): ");
        String idDonacion, cadena;
        String[] dDonacion;
        int idTransformada;
        Donacion donante, nuevo;
        Campania camp = this.buscarCampania(Integer.parseInt(idLeida));
        if(camp != null){
            idDonacion = Herramientas.lector("Ingrese la ID de la donacion(Numerica): ");
            idTransformada = Integer.parseInt(idDonacion);
            donante = camp.buscarDonacion(idTransformada);
            if(donante == null){
                nuevo = crearDonacion(idTransformada);
                camp.agregarDonacion(nuevo);
                dDonacion = nuevo.getDatosDonacionCompleta(); 
                cadena = dDonacion[0]+"*"+dDonacion[1]+"*"+dDonacion[2]+"*"+dDonacion[3]+"*"+dDonacion[4]+"*"+dDonacion[5]+"*"+dDonacion[6]+"*"+idLeida;
                Herramientas.guardarEnArchivo(cadena, "datosDonaciones.txt");
                System.out.println("Donacion agregada correctamente al sistema.");
                this.agregarStockSangre(nuevo.getDonador().getTipoSangre(), 1);
            }else{
                System.out.println("El rut ingresado ya existe.");
            }
        }else{
            System.out.println("La campania con id "+idLeida+" no existe.");
        }
    }
    
    /*Metodo agregarDonacion: A diferencia del anterior agregarDonacion, este
    no solicita datos al usuario, pues ya le llegan por parametors la informacion
    completa del registro.*/
    public void agregarDonacion(Donacion nueva, int id, String tipoSangre){
        Campania aux;
        int x;
        for(x = 0; x < campanias.size(); x++){
            aux = campanias.get(x);
            if(id == aux.getId()){
                aux.agregarDonacion(nueva);
                this.agregarStockSangre(tipoSangre, 1);
            }
        }
    }
    
    /*Metodo agregarCampaniaNueva: Encargado de crear y agregar una
    campania nueva, ingresada por el usuario a traves de la consola.
    Tambien guarda el nuevo registro en el documento de datos.*/
    public void agregarCampaniaNueva() throws IOException{
        int id;
        String localidad, cadena;
        id = Integer.parseInt(Herramientas.lector("Ingrese ID para campania(Numerica): "));
        localidad = Herramientas.lector("Ingrese localidad de la campania : ");
        Campania buscado = buscarCampania(id);
        if(buscado == null){
            Campania nuevo= new Campania(id ,localidad);
            this.agregarCampania(nuevo);
            cadena = id + "*" + localidad;
            Herramientas.guardarEnArchivo(cadena, "datosCampania.txt");
            System.out.println("Campania agregadda correctamente al sistema.");
        }
    }
 
}

