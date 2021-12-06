package Herramientas.matematicas;
import herramientas.matematicas.ExpresionAritmetica;
import entradasalida.EntradaTerminal;
import entradasalida.SalidaTerminal;
import estructuraslineales.ArregloDatos;
import estructuraslineales.ArregloPila;

/**
 * esta clase nos ayuda a poder calcular el resultado de una operacion
 * algebraica respecto a el uso de arreglos y pilas
 * @author  Alexis Ultreras Sotelo
 * @version 1.0
 */
public class CalculadoraPila {
    private ArregloDatos variables;
    private ArregloDatos expresionArit;
    private String cadena;

    /**
     * constructor que nos ayuda a inicializar los arreglos para obtener los resultados
     * @param cadena
     */
    public CalculadoraPila(String cadena) {
        this.cadena = cadena;
        expresionArit = new ArregloDatos(this.cadena.length());
        variables = new ArregloDatos(this.cadena.length());
    }

    /**
     * Indica que la expresion aritmetica tenga variables validas.
     * @return true o false
     */
    private boolean verificarExpresion(){
        String variable = ""; // aqui se almacena el resultado de la operacion
        for (int i = 0; i < cadena.length(); i++) {
            char token = cadena.charAt(i);// obtenemos todos los datos en char de la cadena

            if (token >= 49 && token <= 57) expresionArit.agregar(token); // rango de  0 a 9 en codigo ascii
            else if (!ExpresionAritmetica.esOperando(token))
                expresionArit.agregar(token); // es un operador
            else { // es un caracter del nombre de la variable
                while (true) {
                    if (!(token == '^' || token == '*' || token == '/' || token == '+' ||
                            token == '-' || token == '(' || token == ')')){
                        variable += cadena.charAt(i);
                    } else { // es algun operador
                        i--; // retrocedemos para no perder este caracter obtenido en el while
                        break;
                    }
                    i++;
                    token = cadena.charAt(i);
                }
                if ( restriccionesVariable(variable) ) { // variable valida
                    // agregamos a variablesRegistradas para marcar las variables que se estan usando
                    variables.agregar(variable);
                    expresionArit.agregar(variable);
                    variable = "";
                } else return false; // no cumplio con las reglas que se requerian
            }
        }return true; // la expresion esta bien escrita.
    }

    /**
     * Indica si la variable encontrada en la expresion aritmetica cumple con algunas reglas establecidas en la funcion.
     * @param variable String variable encontrado en la expresion enviada como argumento.
     * @return true o false.
     */
    private boolean restriccionesVariable(String variable){
        if (variable.length()<=30){ // cumple con la longitud
            char temp = variable.charAt(0);
            // verifica si una a variable comienza con una letra o guion bajo.
            if (temp>=65 && temp<=90 || temp>=97 && temp<=122 || temp=='_'){
                for (int i=0;i<variables.cantidadElementos();i++)
                    if (variable.equals(variables.obtener(i).toString())) return false;
                // verificar si la variable solo tiene letras, guiones bajo, números y signosde pesos
                for (int posicion=1;posicion<variable.length();posicion++){
                    temp=variable.charAt(posicion);
                    if (!((temp>=65 && temp<=90) || (temp>=97 && temp<=122) || temp=='_' || !(temp>=49 && temp<=57) || temp=='$'))
                        return false;
                }return true;
            }else return false;
        }else return false;
    }

    /**
     * este metodo Calcula el resultado de la expresion aritmetica
     * que se envio desde el constructor sde la clase
     * @return  resultadoFinal
     */
    public Double calcularExpresion(){
        if (verificarExpresion()) { // las variables que contiene la operacion cumple con lo requerido
            infijaAPostija();  // El arreglo 'expresionAritmetica' que tiene la estructura de infija pasa a ser postfija

            ArregloPila pila = new ArregloPila(expresionArit.cantidadElementos());

            for (int posicion = 0 ; posicion<expresionArit.cantidadElementos() ; posicion++ ){
                Object token = expresionArit.obtener(posicion);
                if (token instanceof  Character){// se verifica si el dato obtenido es de un tipo character
                    if (ExpresionAritmetica.esOperando((char)token)) pila.poner(token);// en caso de que lo sea se castea el dato
                    else {
                        String op2 = pila.quitar().toString();// obtenemos el primer dato quitado
                        String op1 = pila.quitar().toString();// obtenemos el segundo dato quitado

                        if(op2==null || op1==null) {
                            return null;
                        }else {
                            Double resultado = herramientas.matematicas.ExpresionAritmetica.operacionAritmetica(Double.parseDouble(op1),(char)token,Double.parseDouble(op2));
                            if (resultado!=null) pila.poner(""+resultado);
                            else return null;
                        }
                    }
                }else {
                    pila.poner(token);
                }
            }

            String resultadoFinal=(String)pila.quitar();
            if(resultadoFinal==null){
                return null;
            }else {
                return Double.parseDouble(resultadoFinal);
            }
        }else {
            return null;
        }
    }

    /**
     * Convierte la estructura del arreglo 'expresionAritmetica' infija a postfija.
     */
    private void infijaAPostija(){
        ArregloPila pila  = new ArregloPila( expresionArit.cantidadElementos() );
        ArregloDatos nuevaArreglo = new ArregloDatos(expresionArit.cantidadElementos()); // sera nuestra estructura de postfija

        for (int i = 0 ; i<expresionArit.cantidadElementos() ; i++) { // recorre la cadena infija
            Object token = expresionArit.obtener(i);
            if (token instanceof String) { // es una variable, asignamos un valor
                SalidaTerminal.consola("Asigna valor a '"+token+"': ");
                nuevaArreglo.agregar(EntradaTerminal.consolaDouble());
            } else {
                if (ExpresionAritmetica.esOperando((char) token)) { // token es un operando
                    nuevaArreglo.agregar(token);
                } else { // es un operador
                    if (pila.vacio()) pila.poner(token);

                    else if ((char) token == '(') pila.poner(token); // si es parentesis abierto, agrega a la pila
                    else if ((char) token == ')') { // hay una operacion dentro de parentesis
                        while ((char) pila.verTope() != '(') nuevaArreglo.agregar(pila.quitar());
                        pila.quitar(); // sacamos parentesis abierto
                    } else { // priorizamos con jerarquia
                        int pOperador1 = ExpresionAritmetica.prioridadAritmetica((char) token); // operador de token
                        int pOperador2 = ExpresionAritmetica.prioridadAritmetica((char) pila.verTope()); // operador de pila
                        if (pOperador1>pOperador2) { // operador 1 tiene mayor prioridad
                            pila.poner(token);
                        } else if (pOperador1 == pOperador2) { // tienen la misma prioridad
                            nuevaArreglo.agregar(pila.quitar());
                            pila.poner(token);
                        } else { // operador 2 tiene mayor prioridad
                            while (!pila.vacio())
                                nuevaArreglo.agregar(pila.quitar());
                            pila.poner(token);
                        }
                    }
                }
            }
        }
        while(!pila.vacio())
            nuevaArreglo.agregar(pila.quitar());
        expresionArit= nuevaArreglo;
    }
}
