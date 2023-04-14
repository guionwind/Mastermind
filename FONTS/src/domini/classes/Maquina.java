package domini.classes;
import java.util.*;
import java.lang.*;

public interface Maquina {
        /**
         * Given the solution code, the solve operation uses one of the proposed algorithm
         * (either five guess or the genetic one) to create the list of codes that will lead
         * to the solution. If the algorithm is unable to find the solution in less than
         * maxSteps steps, the returned list will contain a list composed of maxSteps codes.
         * The operation will throw an exception in case the secret code solution is not
         * consistent with the parameters of the current game.
         *
         * @param   solution        Solution code of the game to break.
         * @return                  List of the tried codes to break the solution code,
         *                          whether it's achieved or not.
        */
    public List<List<Integer>>  solve (List<Integer> solution) throws Exception;
}