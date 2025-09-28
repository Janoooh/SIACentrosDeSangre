package com.mycompany.siacentrosdesangre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CentroDeSangre {
    private String nombre;
    private ArrayList<Campania> campanias;
    private ArrayList<Persona> personas;
    private HashMap<String, Integer> stockSangre;
    
    public CentroDeSangre(){
        this.campanias = new ArrayList<>();
        this.personas = new ArrayList<>();
        this.stockSangre = new HashMap<>();
    }
    
    public CentroDeSangre(String nombre){
        this.nombre = nombre;
        this.campanias = new ArrayList<>();
        this.personas = new ArrayList<>();
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
 
    /*Metodo buscarPersona: encargado de buscar a una persona
    en el arrayList de persona, con un rut y un tipo dados.*/
    public Persona buscarPersona(String rut, int tipoPersona){
        int x;
        Persona aux;
        
        for(x = 0; x < personas.size(); x++){
            aux = personas.get(x);
            if ((aux.getRut().equals(rut)) &&(aux.getTipoPersona() == tipoPersona))
                return aux;
        }
        return null;
    }
    
    /*Metodo agregarPersona para donante: Encargado de agregar
    una persona al arrayList de personas, especificamente un donante.
    Se encarga de validar que ese donante aun no exista, si es asi, se 
    creara el objeto donante y se guardara en el arrayList de Persona.*/
    public void agregarPersona(String rut, String nom, String tel, int edad, int tipoPersona, String tipoSangre)throws DataDuplicateException{
        Persona buscado, nuevo;
        
        buscado = buscarPersona(rut,tipoPersona);
        
        if(buscado == null){
            nuevo = new Donante(rut,nom,tel,edad,tipoPersona,tipoSangre);
            personas.add(nuevo);
            return;
        }
        throw new DataDuplicateException("El donante con rut "+rut+" ya existe.");
    }
    
    /*Metodo agregarPersona para flebotomista: Encargado de agregar una
    persona al arrayList de personas, especificamente un flebotomista.
    Se encarga de validar que ese flebotomista no exista, en ese caso,
    crea el objeto flebotomista y lo agrega en el arrayList de persona.*/
    public void agregarPersona(String rut, String nom, String tel, int edad, int tipoPersona, String especialidad, String correo)throws DataDuplicateException{
        Persona buscado, nuevo;
        buscado = buscarPersona(rut,tipoPersona);
        if(buscado == null){
            nuevo = new Flebotomista(rut,nom,tel,edad,tipoPersona,especialidad,correo);
            personas.add(nuevo);
            return;
        }
        throw new DataDuplicateException("El flebotomista con rut "+rut+" ya existe.");
        
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
    public Campania buscarCampania(int id){
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
    
    
    //metodo para mostrar la tabla del stock de sangre
    public ArrayList<String> mostrStockSangre(CentroDeSangre centro) throws IOException{
        int i;
        ArrayList<String> datosArchivo = Herramientas.leerArchivo("tiposDeSangre.txt");
        ArrayList<String> retorno = new ArrayList<>();
        String sangre;
        int cantidad;
        for(i= 0; i < datosArchivo.size() ; i ++){
            sangre = datosArchivo.get(i);
            cantidad = getStockSangre(sangre);
            retorno.add(sangre + "*" + Integer.toString(cantidad));
        }
        return retorno;
    }
    
    //Entrega los datos de TODAS las donaciones del sistema.
    public String[][] datosMostrarDonaciones(boolean incluirTipo){
        int nDonaciones, x,y, pos;
        String[][] datosRetorno, datosCampania;
        Campania auxCampania;
        nDonaciones = contarDonaciones();
        datosRetorno = new String[nDonaciones][];
        pos = 0;
        for(x = 0; x < campanias.size(); x++){
            auxCampania = campanias.get(x);
            datosCampania = auxCampania.datosMostrarDonaciones(incluirTipo);
            for (y = 0; y < datosCampania.length; y++){
                datosRetorno[pos] = datosCampania[y];
                pos++;
            }
        }
        return datosRetorno;
    }
    
    public String[][] datosMostrarCampanias(){
        String[][] datosRetorno = new String[campanias.size()][];
        Campania auxCampania;
        int x;
        
        for(x = 0; x < campanias.size(); x++){
            auxCampania = campanias.get(x);
            datosRetorno[x] = auxCampania.getDatosCampania();
        }
        return datosRetorno;
    }
    
    /*Metodo datosMostrarPersona tipo especifico: Encargado
    de recolectar en un arreglo de arreglos String toda la informacion
    de todas las personas de un tipo especificado.*/
    public String[][] datosMostrarPersona(int tipoPersona, boolean incluirTipo){
        int x, pos, nPersonas = contarPersonas(tipoPersona);
        String[][] datosRetorno = new String[nPersonas][];
        Persona aux;
        pos = 0;
        for(x = 0; x < personas.size(); x++){
            aux = personas.get(x);
            if(tipoPersona == 1 && aux.getTipoPersona() == 1){
                datosRetorno[pos] = ((Donante)aux).getInfo(incluirTipo);
                pos++;
            }else if(tipoPersona == 2 && aux.getTipoPersona() == 2){
                datosRetorno[pos] = ((Flebotomista)aux).getInfo(incluirTipo);
                pos++;
            }
        }
        return datosRetorno;
    }
    
    /*Metodo datosMostrarPersona general: Encargado de recolectar
    en un arreglo de arreglos String todos los datos de las personas
    existentes en el arreglo de Personas.*/
    public String[][] datosMostrarPersona(boolean incluirTipo){
        int x;
        Persona aux;
        String[][] datosRetorno = new String[personas.size()][];
        
        for(x = 0; x < personas.size(); x++){
            aux = personas.get(x);
            datosRetorno[x] = aux.getInfo(incluirTipo);
        }
  
        return datosRetorno;
    }
    
    /*Metodo contarPersonas: Encargado de contar cuantas personas
    existen en el arrayList de personas. Recibe tipoPersona, que 
    es un entero que representa el tipo que se desea contar. Sigue
    la logica de la clase, osea si es 1 es donante, y si es 2 es
    flebotomista. Si el valor es 0, se devuelte el total de personas
    existentes en el arrayList.*/
    public int contarPersonas(int tipoPersona){
        Persona aux;
        int cont = 0, x;
        
        if(tipoPersona == 0)return personas.size();
        
        for(x = 0; x < personas.size(); x++){
            aux = personas.get(x);
            if(aux.getTipoPersona() == tipoPersona)
                cont++;
        }
        return cont;
    }
    
    //Cuenta la canitdad de donaciones en el centro
    public int contarDonaciones(){
        int cont, x;
        Campania aux;
        cont = 0;
        
        for(x = 0; x < campanias.size(); x++){
            aux =  campanias.get(x);
            cont += aux.getSizeDonaciones();
        }
        return cont;
    }
    
    /*Metodo mostrarDonacionesDeCampanias: Encargado de mostrar todas las
    donaciones existentes, campania por campania.*/
    /*public void mostrarDonacionesDeCampanias(){
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
    }*/
    
    /*Metodo crearDonacion: Encargado de crear una nueva donacion a traves
    de datos solicitados al usuario por consola. Tras tomar todos los datos,
    retorna un objeto de clase Donacion con sus atributos rellenos.*/
    /*public Donacion crearDonacion(int idDonacion) throws IOException{
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
    }*/
    
    
    /*Metodo agregarDonacion: Encargado de ofrecer la opcion al usuario
    para agregar una nueva donacion a una campania en especifico. Si
    la campania existe, y la donacion presenta un id nuevo, se
    procedera a crear el objeto clase Donacion. Finalmente, la donacion
    se agrega en la campania correspondiente, ademas de guardar el nuevo
    registro en el documento de datos.*/
    /*public void agregarDonacion() throws IOException{
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
    }*/
    
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
    public boolean agregarCampaniaNueva(int id, String localidad) throws IOException,DataDuplicateException{
        String cadena;
        Campania buscado = buscarCampania(id);
        if(buscado == null){
            Campania nuevo= new Campania(id ,localidad);
            agregarCampania(nuevo);
            cadena = id + "*" + localidad;
            //Herramientas.guardarEnArchivo(cadena, "datosCampania.txt");
            return true;
        }
        throw new DataDuplicateException("La campania con id "+id+" ya existe.");
    }
    
    
    //public void agregarDonacionEnCampania(Cam)
    
    //metodo que toma todos los valores para crear una donacion, ademas de una campania, necesario para ventana
    public boolean agregarDonacionACampania(Campania campania, int id, String fecha, String rutDonante, String rutFlebotomista) throws IOException, NotFoundException,DataDuplicateException{
        Persona donante, flebotomista;
        Donacion nueva;
        if(campania.buscarDonacion(id) == null){
            donante = buscarPersona(rutDonante, 1);
            if(donante == null)throw new NotFoundException("El donante con rut "+rutDonante+" no existe.");
            flebotomista = buscarPersona(rutFlebotomista, 2);
            if(flebotomista == null)throw new NotFoundException("El flebotomista con rut "+rutFlebotomista+" no existe.");
            
            nueva = new Donacion(id,fecha,donante,flebotomista);
            campania.agregarDonacion(nueva);
            //String cadena = id + "*" + fecha + "*" + rut + "*" + nombre + "*" + edad + "*" + tipoSangre + "*" + telefono + "*" + campania.getId(); 
            //Herramientas.guardarEnArchivo(cadena, "datosDonaciones.txt");
            agregarStockSangre(((Donante)donante).getTipoSangre(), 1);
            return true;
        }
        
        throw new DataDuplicateException("La donacion con id "+id+" ya existe.");
    }
    
    public Donacion borrarDonacionDeCampania(int idDonacion, int idCampania)throws NotFoundException{
        Campania actual = buscarCampania(idCampania);
        if(actual == null) throw new NotFoundException("La campania "+ idCampania+" no se encontro en el sistema.");
        return actual.borrarDonacion(idDonacion);
         
    }
    
    public Campania borrarCampania(int id)throws NotFoundException{
        int i;
        Campania actual;
        for(i = 0; i<campanias.size(); i++){
            actual = campanias.get(i);
            if(actual.getId()== id){
                campanias.remove(i);
                return actual;
            }
        }
        throw new NotFoundException("La campania "+id+" no se encontro en el sistema.");
    }
    
    public Persona borrarPersona(String rut, int rol)throws NotFoundException{
        int i;
        Persona actual;
        for(i = 0; i < personas.size(); i++){
            actual = personas.get(i);
            if(actual.getRut().equals(rut) && actual.getTipoPersona() == rol){
                personas.remove(i);
                return actual;
            }
        }
        
        if(rol == 1)throw new NotFoundException("El donante con rut "+rut+" no se encontro en el sistema.");
        throw new NotFoundException("El flebotomista con rut "+rut+" no se encontro en el sistema.");
    }
    
}

