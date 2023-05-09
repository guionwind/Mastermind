package persistencia.classes;

import java.io.*;

import domini.classes.exceptions.*;

public abstract class GestorGeneric {
    protected final TipusClasse TIPUS_CLASSE;
    protected final Integer INDEX_ID;
    protected static final String dir = System.getProperty("user.dir") + "/persistencia/data/";
    protected final String file;

    public GestorGeneric(TipusClasse tipusClasse) {
        TIPUS_CLASSE = tipusClasse;
        if (TIPUS_CLASSE == TipusClasse.JUGADOR)
            INDEX_ID = 1;
        else
            INDEX_ID = 0;
        
        String nom = "";
        switch (TIPUS_CLASSE) {
            case JUGADOR:
                nom = "Jugador";
                break;
            case PARTIDA:
                nom = "Partida";
                break;
            case CONFIGURACIOPARTIDA:
                nom = "ConfiguracioPartida";
                break;
            case RANQUING:
                nom = "Ranquing";
                break;
            case FIVEGUESS:
                nom = "FiveGuess";
                break;
            case GENETIC:
                nom = "Genetic";
                break;
            default:
                break;
        }

        file = nom + ".json";
    }

    public String[] obtenir(String identificador) throws IOException, InstanciaNoExisteix {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] dades = line.split(" ", 3); // 3 perquè el identificador de jugador per "username" estara a la segona posicio de la linia
            if (dades[INDEX_ID].equals(identificador)) {
                br.close();
                return line.split(" ");
            }
        }

        br.close();
        throw new InstanciaNoExisteix("Obtenir " + TIPUS_CLASSE + ": La instancia amb identificador '" + identificador + "' no és a l'arxiu.");
    }
    
    public void afegir(String[] instancia) throws IOException, InstanciaJaExisteix {
        if (existeix(instancia[INDEX_ID])) throw new InstanciaJaExisteix("Afegir " + TIPUS_CLASSE + ": La instancia amb identificador '" + instancia[INDEX_ID] + "' ja és a l'arxiu.");


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
            if (dades[INDEX_ID].equals(instancia[INDEX_ID])) {
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
            throw new InstanciaNoExisteix("Actualitzar " + TIPUS_CLASSE + ": La instancia amb identificador '" + instancia[INDEX_ID] + "' no és a l'arxiu.");
        }
        else
            newFile.renameTo(oldFile);

    }
    
    public void eliminar(String identificador) throws IOException, InstanciaNoExisteix {
        File oldFile = new File(dir, file);
        File newFile = new File(dir, "Temporal"+file);

        BufferedReader br = new BufferedReader(new FileReader(oldFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));

        boolean existeix = false;
        String line = null;
        while((line = br.readLine()) != null) {
            String[] dades = line.split(" ", 3);
            if (dades[INDEX_ID].equals(identificador))
                existeix = true;
            else 
                bw.write(line+"\n");
        }
        
        br.close();
        bw.close();
        if (!existeix) {
            newFile.delete();
            throw new InstanciaNoExisteix("Eliminar " + TIPUS_CLASSE + ": La instancia amb identificador '" + identificador + "' no és a l'arxiu.");
        }
        else
            newFile.renameTo(oldFile);
    }

    public boolean existeix(String identificador) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] dades = line.split(" ");
            if (dades[INDEX_ID].equals(identificador)) {
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
