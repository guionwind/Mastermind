package persistencia.classes;

import java.io.*;
import java.util.Base64;

public class Gestor<PersistenceObject> {
    protected static final String dir = System.getProperty("user.dir") + "/FONTS/src/persistencia/data/";
    protected final String file;

    public Gestor(String file) {
        this.file = file;
    }

    protected void afegirObjecte(String id, PersistenceObject obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(dir + file, true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
        bw.write(id);
        bw.newLine();
        
        bw.write(serialitzarObjecte(obj));
        bw.newLine();

        bw.close();
    }

    protected void actualitzarObjecte(String id, PersistenceObject obj) throws IOException {
        File oldFile = new File(dir + file);
        File newFile = new File(dir + "Temporal" + file);
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

    protected PersistenceObject obtenirObjecte(String id) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(dir + file);
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

    public void eliminarObjecte(String id) throws IOException {
        File oldFile = new File(dir + file);
        File newFile = new File(dir + "Temporal" + file);
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

    protected boolean existeixObjecte(String id) throws IOException {        
        FileInputStream fis = new FileInputStream(dir + file);
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

    private String serialitzarObjecte(PersistenceObject obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(obj);
        
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private PersistenceObject deserialitzarObjecte(String configuracioPartida) throws IOException, ClassNotFoundException {
        byte[] bytes = Base64.getDecoder().decode(configuracioPartida);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);

        PersistenceObject obj = null;
        obj = (PersistenceObject) ois.readObject();
        
        ois.close();
        return obj;
    }
}
