package persistencia.classes;

import java.beans.PersistenceDelegate;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

public class Gestor<PersistenceObject> {
    /**
     * Directori on es guarden les dades.
     */
    private static final String dirPath = System.getProperty("user.dir") + "/persistencia/data/";
    /**
     * Nom de l'arxiu on es guarden les dades.
     */
    private final String fileName;
    
    
    /**
     * Constructora
     * 
     * @param fileName          Nom de l'arxiu on es guarden les dades
     * @throws IOException      Llença una excepció si hi ha hagut algún
     *                          problema amb l'entrada o sortida de dades.
     */
    public Gestor(String fileName) throws IOException {
        this.fileName = fileName;

        File d = new File(dirPath);
        if (!d.exists()) d.mkdir();

        File f = new File(dirPath, fileName);
        if (!f.exists()) f.createNewFile();
    }

    /**
     * Guarda una nova intància de l'objecte
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param obj                       Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    protected void afegirObjecte(String id, PersistenceObject obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(dirPath + fileName, true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
        bw.write(id);
        bw.newLine();
        
        bw.write(serialitzarObjecte(obj));
        bw.newLine();

        bw.close();
    }

    /**
     * Actualitza una nova intància de l'objecte
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param obj                       Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    protected void actualitzarObjecte(String id, PersistenceObject obj) throws IOException {
        File oldFile = new File(dirPath + fileName);
        File newFile = new File(dirPath + "Temporal" + fileName);
        newFile.createNewFile();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(oldFile)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));

        String line = null;
        while((line = br.readLine()) != null) {
            String cp = br.readLine();

            bw.write(line);
            bw.newLine();

            if (line.equals(id)) bw.write(serialitzarObjecte(obj));
            else bw.write(cp);

            bw.newLine();
        }
        
        br.close();
        bw.close();

        newFile.renameTo(oldFile);
    }

    /**
     * Esborra una nova intància de l'objecte
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param obj                       Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    protected PersistenceObject obtenirObjecte(String id) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(dirPath + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        PersistenceObject obj = null;
        while ((line = br.readLine()) != null && obj == null) {
            String objSer = br.readLine();
            if (line.equals(id))
                obj = deserialitzarObjecte(objSer);
        }

        br.close();
        return obj;
    }

    /**
     * Elimina una nova intància de l'objecte
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @param obj                       Instància a guardar.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    public void eliminarObjecte(String id) throws IOException {
        File oldFile = new File(dirPath + fileName);
        File newFile = new File(dirPath + "Temporal" + fileName);
        newFile.createNewFile();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(oldFile)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));

        String line = null;
        while((line = br.readLine()) != null) {
            String cp = br.readLine();
            
            if (!line.equals(id)) {
                bw.write(line);
                bw.newLine();
                
                bw.write(cp);
                bw.newLine();
            }
        }
        
        br.close();
        bw.close();

        newFile.renameTo(oldFile);
    }

    /**
     * Indica si una intància de l'objecte existeix
     * amb l'identificador donat.
     * 
     * @param id                        Identificador de la instància a guardar.
     * @return                          Cert si la instància està guardada i
     *                                  fals en cas contrari.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    protected boolean existeixObjecte(String id) throws IOException {
        FileInputStream fis = new FileInputStream(dirPath + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.equals(id)) {
                br.close();
                return true;
            }
            br.readLine(); // Saltem una línia perquè és la linia del objecte serialitzat
        }

        br.close();
        return false;
    }

    /**
     * Retorna quantes intancies del tipus d'objecte existeixen.
     * 
     * @return                          Nombre d'instàncies del tipus d'objecte.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    protected int nombreObjectes() throws IOException {
        FileInputStream fis = new FileInputStream(dirPath + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        int nombreObjectes = 0;
        while ((br.readLine()) != null) {
            ++nombreObjectes;
            br.readLine(); // Saltem una línia perquè és la linia del objecte serialitzat
        }

        br.close();
        return nombreObjectes;
    }

    protected ArrayList<String> obtenirIdentificadors() throws IOException {
        FileInputStream fis = new FileInputStream(dirPath + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        ArrayList<String> identificadors = new ArrayList<String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            identificadors.add(line);
            br.readLine(); // Saltem una línia perquè és la linia del objecte serialitzat
        }

        br.close();
        return identificadors;
    }
    
    protected ArrayList<PersistenceObject> obtenirObjectes(String id) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(dirPath + fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        ArrayList<PersistenceObject> objectes = new ArrayList<PersistenceObject>();
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] identificadors = line.split(" ");
            boolean trobat = false;
            for (int i=0; i<identificadors.length && !trobat; ++i) {
                if (identificadors[i].equals(id)) {
                    trobat = true;
                    String objSer = br.readLine();
                    objectes.add(deserialitzarObjecte(objSer));
                }
            }
            if (!trobat)
                br.readLine(); // Saltem una línia perquè és la linia del objecte serialitzat
        }

        br.close();
        return objectes;
    }

    /**
     * Serialitza l'objecte i el converteix en String.
     * 
     * @param obj                       Objecte a serialitzar.
     * @return                          La representació en String de
     *                                  l'objecte serialitzat.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     */
    private String serialitzarObjecte(PersistenceObject obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(obj);
        
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    /**
     * Deserialitza l'objecte
     * 
     * @param obj                       Objecte a deserialitzar.
     * @return                          La instància de l'objecte.
     * @throws IOException              Llença una excepció si hi ha hagut algún
     *                                  problema amb l'entrada o sortida de dades.
     * @throws ClassNotFoundException   Llença una excepció si la classe de la instància
     *                                  no s'ha localitzat.
     */
    private PersistenceObject deserialitzarObjecte(String obj) throws IOException, ClassNotFoundException {
        byte[] bytes = Base64.getDecoder().decode(obj);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);

        PersistenceObject instancia = null;
        instancia = (PersistenceObject) ois.readObject();
        
        ois.close();
        return instancia;
    }
}
