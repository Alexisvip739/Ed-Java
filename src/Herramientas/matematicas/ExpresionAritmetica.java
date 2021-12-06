package herramientas.matematicas;

import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ArregloPila;

/**
 * clase que implementa todas las expresiones con la logica necesaria
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class ExpresionAritmetica {
    /**
     * metodo que nos ayuda a obtener el resultado de una formula algebraica de forma infija a postfija
     *
     * @param infija
     * @return cadena
     */
    public static String pasarEInfijaAPostfija(String infija){
        ArregloPila pila  = new ArregloPila( infija.length() );
        String cadena="";
        for (int posicion = 0 ; posicion<infija.length() ; posicion++){ // recorre la cadena infija
            char token = infija.charAt( posicion );
            if (esOperando( token)){ // el caracter no es operador
                cadena+=token;
            }else{ // es un operador
                if (pila.vacio())   pila.poner( token );

                else if (token=='(')  pila.poner( token );

                else if (token==')'){
                    while ((char)pila.verTope()!='(') cadena+=pila.quitar();
                    pila.quitar();
                }
                else { // priorizamos con jerarquia
                    int pToken = prioridadAritmetica( token ); // caracter
                    int pPila = prioridadAritmetica( (char)pila.verTope() ); // elemento final de la pila
                    if ( pToken>pPila ){ // el caracter tiene mayor prioridad
                        pila.poner( token );
                    }else if( pToken==pPila ){ // el caracter tienen la misma prioridad
                        cadena+=pila.quitar();
                        pila.poner( token );
                    }else{ // el caracter tiene menor prioridad
                        while(!pila.vacio())
                            cadena+=pila.quitar();
                        pila.poner( token );
                    }
                }
            }
        }
        while(!pila.vacio())
            cadena+=pila.quitar();
        return cadena;
    }

    /**
     * esta clase nos ayuda a obtener  el resultado de una operacion aritmetica de infia a prefija
     * @param infija
     * @return cadena
     */
    public static String pasarEInfijaAPrefija(String infija){
        infija = "(" + infija; // Agregamos al inicio del infijo un paréntesis, para saber el inicio.
        ArregloPila PilaExpre = new ArregloPila(infija.length());
        ArregloPila PilaTemp = new ArregloPila(infija.length());
        for (int i = infija.length()-1; i >= 0; i--) {
            char token = infija.charAt(i); //Obtenemos el token
            if(token  == ')'){ //Es parentésis cerrado
                break;
            }else if((token != '(' && token != ')') && token == '^' | token == '*' |token == '/' |
                    token == '+' |token == '-'){ //Es un operador
                while (!PilaTemp.vacio() && PilaTemp.verTope().toString().charAt(0) != ')' &&
                        prioridadAritmetica(token) > prioridadAritmetica((Character) PilaTemp.verTope())){ //Comprobamos la prioridad.
                    PilaExpre.poner(PilaTemp.quitar()); //Lo agregamos a la Pila de la expresión.
                };
                PilaTemp.poner(token); // Agregamos el token.
            }else if(token == '('){ //Es un parentésis abierto.
                while (!PilaTemp.vacio() && PilaTemp.verTope().toString().charAt(0) != ')'){ //
                    PilaExpre.poner(PilaTemp.quitar()); //Lo agregamos a la pila de la expresión.
                }
                PilaTemp.quitar();
            }else{
                PilaExpre.poner(token);
            }
        }
        //Lo convertimos a string.
        String exp = "";
        while(!PilaExpre.vacio()){ //Recorremos la pila
            exp += PilaExpre.quitar(); //Agregos a la cadena
        }
        return exp; //Expresión prefija
    }

    /**
     * este metodo nos ayuda a calcular el resultado de una ecuacion de forma postfija
     * @param postfija
     * @return resultadoE
     */
    public static Double evaluarEPostfija(String postfija){
        double resultadoE=0.0;
        ArregloPila pila= new ArregloPila(postfija.length());

        //0. Tokenizar la cadena en postfija
        for(int posToken=0;posToken<postfija.length();posToken++){
            char token=postfija.charAt(posToken);
            //1. Si es un operando, se mete en la pila
            if(esOperando(token)==true){
                pila.poner(""+token);
            }else{ //es un operador
                //2. Si es un operador, saco dos operandos de la pila, les aplico la operación
                // y el resultado lo meto en la pila.
                String operando2=(String)pila.quitar();
                String operando1=(String)pila.quitar();

                if(operando2==null || operando1==null){
                    return null;
                }else{ //son valores válidos
                    Double resultadoParcial=operacionAritmetica(Double.parseDouble(operando1.toString()),token,
                            Double.parseDouble(operando2.toString()));
                    if (resultadoParcial!=null) {
                        pila.poner("" + resultadoParcial);
                    }else{ //ocurrión un detalle
                        return null;
                    }
                }
            }
        }
        //3. Regresamos el valor del resultado que se enuentra en la cima de la pila
        String resultadoFinal=(String)pila.quitar();
        if(resultadoFinal==null){
            return null;
        }else { //todo salió bien
            resultadoE=Double.parseDouble(resultadoFinal);
            return resultadoE;
        }

    }

    /**
     * metodo que nos ayuda a evaluar una operacoin algebraica de forma prefija
     * @param prefija
     * @return resultadoFinal
     */
    public static Double evaluarEPrefija(String prefija){
        ArregloPila pila=new ArregloPila(prefija.length());

        //Tokenizar la E prefija     <----- -(0)  x(1)  *(2)  /(3)  y(4)  +(5)  f(6)  g(7)  ^(8)  h(9)  q(10)
        for(int posToken= (prefija.length() -1); posToken>=0;posToken-- ){
            //saco el token
            char token=prefija.charAt(posToken);

            //Si el token:
            //- Operando, se mete en una pila.
            if(esOperando(token)){
                if(pila.poner(""+token)==false){ //hubo algún error y no hay espacio en la pila
                    return null;
                }
            }else{
                //- Operador, se sacan dos operandos de la pila
                //(el primer elemento que se saca es OP1),
                //se aplica la operación del token y el resultado
                //se mete en la pila.
                String operando1=(String)pila.quitar();
                String operando2=(String)pila.quitar();

                if(operando1==null || operando2==null){ //hubo un error
                    return null;
                }else{ //si se pudo sacar de la pila dos elementos
                    Double resultadoTemporal=operacionAritmetica(Double.parseDouble(operando1), token,
                            Double.parseDouble(operando2));
                    if(resultadoTemporal==null){ //hubo un problema
                        return null;
                    }else{ //no hubo problema
                        pila.poner(""+resultadoTemporal);
                    }
                }
            }
        } //for de tokenizar
        //NOTA: El resultado final de la evaluación
        //está guardado en el tope de la pila.
        String resultadoFinal=(String)pila.quitar();
        if(resultadoFinal==null){
            return null; //la evaluación regresa null
        }else {
            return Double.parseDouble(resultadoFinal);
        }
    }


    /**
     * metodo que nos ayuda a calculad el resultado de dos operando y un operador dependiendo de el caso
     * @param operando1
     * @param operador
     * @param operando2
     * @return regresa el resultado de las dos operaciones
     */
    public static Double operacionAritmetica(double operando1, char operador, double operando2){
        if(operador=='^'){
            return Math.pow(operando1,operando2);
        }else if(operador=='*'){
            return operando1*operando2;
        }else if(operador=='/'){
            return operando1/operando2;
        }else if(operador=='+'){
            return operando1+operando2;
        }else if(operador=='-'){
            return operando1-operando2;
        }else{
            return null;
        }
    }

    /**
     * verifica si el dato pasado como parametro es un operando
     * @param token
     * @return true o false
     */
    public static boolean esOperando(char token){
        if(token=='^' || token=='*' || token=='/' || token=='+' || token=='-' || token=='(' || token==')'){
            return false; //no es operando
        }else{
            return true; //si es operando
        }
    }

    /**
     * Devuelve una prioridad dependiendo del tipo de operador.
     * @param token Operador que se le dara su prioridad.
     * @return regresa una prioridad de acuerdo al operador, en caso contrario, regresa 0.
     */
    public static int  prioridadAritmetica(char token){
        if (token == '^'){ return 3; }
        if (token == '*' || token == '/') { return 2; }
        if (token == '+' || token == '-') { return 1; }
        return 0; // no es ningun operador.
    }
}


