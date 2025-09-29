package com.mycompany.siacentrosdesangre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que representa una entidad que organiza campañas de donación de sangre.
 */
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
 
    /**
     * Método que busca una persona que coincida con un rut y un rol expecifico.
     * Recorre el ArrayList de persona buscando una que tenga tanto el rut como el rol dado.
     * @param rut identificador de la persona buscada
     * @param tipoPersona tipo de persona. Donante (1) o Flebotomista(2)
     * @return el objeto Persona buscado. Si no lo encuentra retorna null.
     */
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
    
    /**
     * Método para agregar una persona, pensado especificamente para un donante.
     * Llama al método para buscar una persona dentro del arrayList.
     * Se crea un nuevo donante pasandole todos los datos al constructor y luego
     * se agrega al arrayList de personas.
     * @param rut identificador de la persona.
     * @param nom nombre de la persona.
     * @param tel número de teléfono de la persona.
     * @param edad edad de la persona.
     * @param tipoPersona indica el tipo de persona. En este caso es Donante(1).
     * @param tipoSangre indica el tipo de sangre del donante.
     * @throws DataDuplicateException excepcion en caso de que el rut ya esté registrado como donante en el sistema.
     */
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
    
    /**
     * Método para agregar una persona (sobrecarga), pensado para un flebotomista.
     * Llama al método para buscar una persona por su rut y su tipo.
     * Se crea un nuevo Flebotomista pasandole todos los datos al constructor y luego
     * se agrega al arrayList de personas.
     * @param rut identificador de la persona.
     * @param nom nombre de la persona.
     * @param tel número de teléfono de la persona.
     * @param edad edad de la persona.
     * @param tipoPersona indica el tipo de persona. En este caso es Flebotomista(2).
     * @param especialidad indica la especialidad del encargado de extraer sangre.
     * @param correo indica el correo del especialista.
     * @throws DataDuplicateException excepcion en caso de que ya exista un flebotomista con ese rut.
     */
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
        String[] sangres = {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
        for(x = 0; x < sangres.length ; x ++){
            this.agregarStockSangre(sangres[x]);
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
    
    
    /*Metodo mostrarStockSangre: Encargado de crear un ArrayList de cadenas,
    donde cada cadena representa una relacion de tipoSangre-cantidadDisponible.*/
    public ArrayList<String> mostrarStockSangre(CentroDeSangre centro, String rutaSangre){
        int i, cantidad;
        String[] sangres = {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
        ArrayList<String> retorno = new ArrayList<>();
        
        for(i= 0; i < sangres.length ; i++){
            cantidad = getStockSangre(sangres[i]);
            retorno.add(sangres[i] + "*" + Integer.toString(cantidad));
        }
        return retorno;
    }
    
    /*Metodo datosMostrarDonaciones: Encargado de crear y llenar
    un arreglo de arreglos de String, donde cada uno de esos arreglos
    tiene los datos de las Donaciones en string.*/
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
    
    /*Metodo datosMostrarCampanias: Encargado de crear y llenar
    un arreglo de arreglos string, donde cada uno de esos arreglos
    guarda los datos en String de las campanias.*/
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
            //cadena = id + "*" + localidad;
            //Herramientas.guardarEnArchivo(cadena, "datosCampania.txt");
            return true;
        }
        throw new DataDuplicateException("La campania con id "+id+" ya existe.");
    }
    
    /*metodo que toma todos los valores para crear una donacion, ademas de una campania, 
    necesario para ventana*/
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
    
    /**
     * Método que elimina una donacion de una campaña.
     * Llama al método para buscar la campania, si existe en el sistema, entonces llama al método del objeto campania
     * para eliminar la donacion.
     * @param idDonacion identificador de la donacion buscada.
     * @param idCampania identificador de la campaña
     * @return el objeto Donacion eliminado.
     * @throws NotFoundException Excepcion en caso que no haya una campaña con el id dado.
     */
    public Donacion borrarDonacionDeCampania(int idDonacion, int idCampania)throws NotFoundException{
        Campania actual = buscarCampania(idCampania);
        if(actual == null) throw new NotFoundException("La campania "+ idCampania+" no se encontro en el sistema.");
        return actual.borrarDonacion(idDonacion);
         
    }
    
    /**
     * Método para eliminar una campaña del arrayList que almacena campañas.
     * Recorre el ArrayList campanias buscando si existe un objeto Campania con un id que coincida con el buscado.
     * Si existe lo elimina del arraylist campanias y lo retorna.
     * @param id identificador de la campaña buscada.
     * @return el objeto Campania eliminado.
     * @throws NotFoundException excepcion en caso que no exista una campaña con el id dado.
     */
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
    
    /**
     * Método para eliminar una persona del arrayList de personas.
     * Recorre el arrayList de personas buscando una Persona que coincida con el rut y el tipo dado.
     * Si existe lo retorna.
     * @param rut identificador de la persona.
     * @param rol identifica el tipo de persona. Donante = 1 ; Flebotomista = 2.
     * @return objeto Persona que fue eliminado del arrayList personas
     * @throws NotFoundException excepcion en caso de que la persona con el rut buscado no exista en el sistema.
     */
    
          
    /*Metodo desvincular: Encargado de desvincular a una persona
    de una donacion.*/
    private void desvincular(Persona persona){
        int i;
        Campania actual;
        for(i = 0; i<campanias.size(); i++){
            actual = campanias.get(i);
            actual.desvincularPersonaDeDonacion(persona);
        }
    }
    
    /*Metodo borrarPersona: Encargado de quitar del sistema a
    una persona con cierto rol, los cuales son especificados por
    los parametros rut y rol. Usa desvincular para quitar su presencia
    en los posibles registros(Donaciones) que existan de ellos.*/
    public Persona borrarPersona(String rut, int rol)throws NotFoundException{
        int i, j;
        Persona actual;
        for(i = 0; i < personas.size(); i++){
            actual = personas.get(i);
            if(actual.getRut().equals(rut) && actual.getTipoPersona() == rol){
                desvincular(actual);
                personas.remove(i);
                return actual;
            }
        }
        
        if(rol == 1)throw new NotFoundException("El donante con rut "+rut+" no se encontro en el sistema.");
        throw new NotFoundException("El flebotomista con rut "+rut+" no se encontro en el sistema.");
    }
    
    /*Metodo guardarPersonas: Encargado de guardar los datos de 
    las personas del sistema en el archivo de la ruta
    dada por parametro. Permite la persistencia de datos para 
    los registros de persona.*/
    public void guardarPersonas(String rutaPers){
        Persona aux;
        String[] datosPers;
        String linea;
        int x;
        
        Herramientas.limpiarArchivo(rutaPers);
        for(x = 0; x < personas.size(); x++){
            aux = personas.get(x);
            datosPers = aux.getInfo(true);
            if(aux.getTipoPersona() == 1)
                datosPers[4] = "1";
            else if(aux.getTipoPersona() == 2)
                datosPers[4] = "2";
            
            linea = String.join("|", datosPers);
            try{
                Herramientas.guardarEnArchivo(linea, rutaPers);
            }catch(Exception e){
                System.out.println("ERROR : "+e.getMessage());
            }
        }
    }
    
    /*Metodo guardarCampanias: Encargado de guardar las donaciones
    del sistema en el archivo de la ruta dada por parametro. Permite
    la persistencia de datos para las campanias.*/
    public void guardarCampanias(String rutaCamp){
        Campania aux;
        String[] datosCamp;
        String linea;
        int x;
        
        Herramientas.limpiarArchivo(rutaCamp);
        for(x = 0; x < campanias.size(); x++){
            aux = campanias.get(x);
            datosCamp = aux.getDatosCampania();
            linea = datosCamp[0] + "|" + datosCamp[1];
            try{
                Herramientas.guardarEnArchivo(linea, rutaCamp);
            }catch(Exception e){
                System.out.println("ERROR : "+e.getMessage());
            }
        }
    }
    
    /*Metodo guardarDonaciones: Encargado de guardar las donaciones
    del sistema en el archivo dado por la ruta pasada por parametro.
    Permite la persistencia de datos de las donaciones.*/
    public void guardarDonaciones(String rutaDona){
        int x;
        Herramientas.limpiarArchivo(rutaDona);
        for(x = 0; x < campanias.size(); x++){
            campanias.get(x).guardarDonaciones(rutaDona);
        }
    }
    
    /*Metodo guardarSangres: Encargado de escribir en la ruta dada la
    cantida de sangre que existe en el stock. Permite la persistencia
    de datos para el stock de sangre.*/
    public void guardarSangres(String rutaSangre){
        String[] sangres = {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
        int x;
        
        Herramientas.limpiarArchivo(rutaSangre);
        try{
            for(x = 0; x < sangres.length; x++)
                Herramientas.guardarEnArchivo(String.valueOf(getStockSangre(sangres[x])), rutaSangre);
            
        }catch(Exception e){
            System.out.println("ERROR : "+e.getMessage());
        }
    }
    
    
    /*
    Metodo propio del negocio para encontrar las Campanias con mas donaciones sobre cierto umbral, este metodo 
    nos sirve para ver en que zonas son mas efectivas 
    las campanias de donaciones de sangre segun el umbral establecido.
    */
    public String[][] campaniasSobreUmbral(int umbral){
        int i, cantidad;
        ArrayList<String[]> listaMejores = new ArrayList<>();
        Campania actual;
        String[][] datosRetorno;
        String[] datosCamp;
        
        for(i = 0; i < campanias.size(); i++){
            actual = campanias.get(i);
            cantidad = actual.getSizeDonaciones();
            if(cantidad > umbral){
                datosCamp = new String[]{String.valueOf(actual.getId()),actual.getLocalidad(),String.valueOf(cantidad)};
                listaMejores.add(datosCamp);
            }
        }
        datosRetorno = new String[listaMejores.size()][];
        for(i = 0; i < listaMejores.size(); i++)
            datosRetorno[i] = listaMejores.get(i);
        
        
        return datosRetorno;
    }
    
    /*Metodo getMayorCantDonaciones: Encargado de obtener la cantidad
    de mayor numero de donaciones entre todas las campanias. Devuelve
    dicha cantidad en un int.*/
    public int getMayorCantDonaciones(){
        int x , nDonaciones, mayor = 0;
        Campania aux;
        for(x = 0; x < campanias.size(); x++){
            aux = campanias.get(x);
            nDonaciones = aux.getSizeDonaciones();
            if(nDonaciones > mayor){
                mayor = nDonaciones;
            }
        }
        return mayor;
    }
    
    /*Metodo getCampaniasMayorDonacion: Encargado de obtener
    las campanias con mayor numero de donaciones. Devuelve un
    ArrayList de campanias, que son las que tienen el mayor numero
    de donaciones.*/
    public ArrayList<Campania> getCampaniasMayorDonaciones(){
        ArrayList<Campania> retorno = new ArrayList<Campania>();
        Campania aux;
        int x, mayor;
        mayor = getMayorCantDonaciones();
        
        for(x = 0; x < campanias.size(); x++){
            aux = campanias.get(x);
            if(aux.getSizeDonaciones() == mayor)
                retorno.add(aux);
        }
        
        return retorno;
    }
    
    /*Metodo generarReporteCsv: Encargado de crear el reporte csv
    en la ruta que le llega por parametro. Realiza distintos informes
    de estadistica sobre el centro de sangre, en los que se destaca
    el mostrar las campanias con mas donaciones, y el porcentaje
    de donaciones que representa cada campania en el sistema.*/
    public void generarReporteCsv(String rutaReporte){
        String linea;
        int x, mayorCantidad, nDonaciones;
        ArrayList<Campania> campaniasMayorDona;
        float porcentaje;
        Campania auxCamp;
        try{
            Herramientas.limpiarArchivo(rutaReporte);
            
            linea = "Nombre del centro;"+nombre;
            Herramientas.guardarEnArchivo(linea, rutaReporte);
            
            linea = "Cantidad de Campanias;"+campanias.size();
            Herramientas.guardarEnArchivo(linea, rutaReporte);
            
            linea = "Cantidad de Donantes;"+contarPersonas(1);
            Herramientas.guardarEnArchivo(linea, rutaReporte);
            
            linea = "Cantidad de Flebotomistas;"+contarPersonas(2);
            Herramientas.guardarEnArchivo(linea, rutaReporte);
            
            linea = "Cantidad TOTAL de Personas;"+contarPersonas(0);
            Herramientas.guardarEnArchivo(linea, rutaReporte);
            
            nDonaciones = contarDonaciones();
            linea = "Cantidad de Donaciones;"+nDonaciones;
            Herramientas.guardarEnArchivo(linea, rutaReporte);
            
            mayorCantidad = getMayorCantDonaciones();
            campaniasMayorDona = getCampaniasMayorDonaciones();
            linea = "Campanias con MAYOR cantidad de Donaciones: "+mayorCantidad+";ID;LOCALIDAD";
            Herramientas.guardarEnArchivo(linea,rutaReporte);
            
            for(x = 0; x < campaniasMayorDona.size(); x++){
                auxCamp = campaniasMayorDona.get(x);
                linea = ";"+auxCamp.getId()+";"+auxCamp.getLocalidad();
                Herramientas.guardarEnArchivo(linea, rutaReporte);
            }
            
            linea = "Campanias y su % representativo de Donaciones;ID;LOCALIDAD;CANT DONACIONES; %REPRESENTATIVO";
            Herramientas.guardarEnArchivo(linea, rutaReporte);
            
            for(x = 0; x < campanias.size();x++){
                auxCamp = campanias.get(x);
                porcentaje = (auxCamp.getSizeDonaciones()/(float)nDonaciones) * 100;
                linea = ";"+auxCamp.getId()+";"+auxCamp.getLocalidad()+";"+auxCamp.getSizeDonaciones()+";"+porcentaje;
                Herramientas.guardarEnArchivo(linea, rutaReporte);
            }
            
            return;
            
        }catch(IOException e){
            System.out.println("Error al escribir en archivo :"+e.getMessage());
        }catch(Exception e){
            System.out.println("ERROR :"+e.getMessage());
        }
    }
    
    
}

