package persistencia.classes;

import java.io.*;

import domini.classes.exceptions.*;

public class GestorEstadistiquesPartida {
    protected final TipusClasse TIPUS_CLASSE;
    protected static final String dir = System.getProperty("user.dir") + "/persistencia/data/";
    protected final String file;
    
    public GestorEstadistiquesPartida() {
        TIPUS_CLASSE = TipusClasse.ESTADISTIQUESPARTIDA;
        file = "EstadistiquesPartida" + ".txt";
    }

    public String[] obtenir(String identificador1, String identificador2) throws IOException, InstanciaNoExisteix {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] dades = line.split(" ", 3); // 3 perquè el identificador de estadistiques partida son 2, idJugador i idPartida
            if (dades[0].equals(identificador1) && dades[1].equals(identificador2)) {
                br.close();
                return line.split(" ");
            }
        }

        br.close();
        throw new InstanciaNoExisteix("Obtenir " + TIPUS_CLASSE + ": La instancia amb identificadors '" + identificador1 + "' i '" + identificador2 + "' no és a l'arxiu.");
    }
    
    public void afegir(String[] instancia) throws IOException, InstanciaJaExisteix {
        if (existeix(instancia[0], instancia[1])) throw new InstanciaJaExisteix("Afegir " + TIPUS_CLASSE + ": La instancia  amb identificadors '" + instancia[0] + "' i '" + instancia[1] + "' ja és a l'arxiu.");


        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        StringBuilder dades = new StringBuilder();

        for (int i=0; i<instancia.length; ++i)
            dades.append(instancia[i] + " ");
        dades.append("\n");

        bw.write(dades.toString()+"\n", 0, dades.length());
        bw.close();
    }

    public void actualitzar(String[] instancia) throws IOException, InstanciaNoExisteix {
        File oldFile = new File(dir, file);
        File newFile = new File(dir, "Temporal"+file);

        BufferedReader br = new BufferedReader(new FileReader(oldFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));

        boolean existeix = false;
        String line = null;
        while((line = br.readLine()) != null) {
            String[] dades = line.split(" ", 3);
            if (dades[0].equals(instancia[0]) && dades[1].equals(instancia[1])) {
                existeix = true;
                bw.write(String.join(" ", instancia)+"\n");
            }
            else 
                bw.write(line+"\n");
        }
        
        br.close();
        bw.close();
        if (!existeix) {
            newFile.delete();
            throw new InstanciaNoExisteix("Actualitzar " + TIPUS_CLASSE + ": La instancia  amb identificadors '" + instancia[0] + "' i '" + instancia[1] + "' no és a l'arxiu.");
        }
        else
            newFile.renameTo(oldFile);

    }
    
    public void eliminar(String identificador1, String identificador2) throws IOException, InstanciaNoExisteix {
        File oldFile = new File(dir, file);
        File newFile = new File(dir, "Temporal"+file);

        BufferedReader br = new BufferedReader(new FileReader(oldFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));

        boolean existeix = false;
        String line = null;
        while((line = br.readLine()) != null) {
            String[] dades = line.split(" ", 3);
            if (dades[0].equals(identificador1) && dades[1].equals(identificador2))
                existeix = true;
            else 
                bw.write(line+"\n");
        }
        
        br.close();
        bw.close();
        if (!existeix) {
            newFile.delete();
            throw new InstanciaNoExisteix("Eliminar " + TIPUS_CLASSE + ": La instancia  amb identificadors '" + identificador1 + "' i '" + identificador2 + "' no és a l'arxiu.");
        }
        else
            newFile.renameTo(oldFile);
    }

    public boolean existeix(String identificador1, String identificador2) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] dades = line.split(" ");
            if (dades[0].equals(identificador1) && dades[1].equals(identificador2)) {
                br.close();
                return true;
            }
        }

        br.close();
        return false;
    }

    public Integer obtenirNumeroTotal() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        Integer lines = 0;
        while (br.readLine() != null)
            ++lines;

        br.close();
        return lines;
    }

}
