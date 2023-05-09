Format de les dades guardades als arxius:
    - Una instancia de classe per línia
    - Atributs separats per espais

    Jugador: id username password

    ConfiguracioPartida: idPartida tipusPartida numeroIntents numeroColors longitudCombinacio

    EstadistiquesPartida: idJugador idPartida puntuacio temps guanyada

    (TIPUS_PARTIDA = [Codebreaker / Codemaker])
    Partida: id TIPUS_PARTIDA solutionCode_fitxa_1 solutionCode_fitxa_2 ... solutionCode_fitxa_N idRonda_1 combinacioIntentada_fitxa_1 combinacioIntentada_fitxa_2 ... combinacioIntentada_fitxa_N idRonda_2 combinacioIntentada_fitxa_1 combinacioIntentada_fitxa_2 ... combinacioIntentada_fitxa_N ... idRonda_M combinacioIntentada_fitxa_1 combinacioIntentada_fitxa_2 ... combinacioIntentada_fitxa_N

    Ranquing: id idJugador_1 puntuacio_1 idJugador_2 puntuacio_2 ... idJugador_N puntuacio_N
    
    FiveGuess: idPartida codi_disponible_1 codi_disponible_2 ... codi_disponible_N codi_possible_1 codi_possible_2 ... codi_possible_M

    Genetic: idPartida 