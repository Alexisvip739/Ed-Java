package estructurasnolineales;

import entradasalida.SalidaTerminal;
import estructuraslineales.registros.NodoDoble;
import herramientas.generales.Utilerias;

import javax.security.auth.Subject;

public class ABB extends ArbolBinario{

    public boolean agregar(Object elemento){
        if(raiz==null){ // no hay nada en el Ã¡rbol
            NodoDoble nuevoNodo=new NodoDoble(elemento);
            if(nuevoNodo!=null){ //si hay espacio
                raiz=nuevoNodo;
                return true;
            }else{ //se asume que no hay espacio
                raiz=null;
                return false;
            }
        }else{ //ya hay algo en el Ã¡rbol
            return agregar(raiz, elemento);
        }
    }

    private boolean agregar(NodoDoble subRaiz, Object elemento){
        //Verificar que el elemento proporcionado sea

        if(Utilerias.compararObjetos(elemento,subRaiz.getDato())>0){ //elemento > subRaiz.dato
            //Mayor a la informaciÃ³n de la subRaiz
            if(subRaiz.getDirMemDer()==null){ // no tiene hijo derecho, ahÃ­ le toca insertarse a elemento
                NodoDoble nuevoNodo=new NodoDoble(elemento);
                if (nuevoNodo!=null){ //si hay espacio
                    subRaiz.setDirMemDer(nuevoNodo);
                    return true;
                }else{ //no hay espacio
                    return false;
                }
            }else{ //ya tiene un hijo derecho esta subRaiz, nos dirigimos hacia esa Rama,
                return agregar(subRaiz.getDirMemDer(), elemento);
            }
        } else if(Utilerias.compararObjetos(elemento,subRaiz.getDato())<0){ //elemento < subRaiz.dato
            // Menor a la informaciÃ³n de la subRaiz
            if(subRaiz.getDirMemIzq()==null){ // no tiene hijo izquierdo, ahÃ­ le toca insertarse a elemento
                NodoDoble nuevoNodo=new NodoDoble(elemento);
                if (nuevoNodo!=null){ //si hay espacio
                    subRaiz.setDirMemIzq(nuevoNodo);
                    return true;
                }else{ //no hay espacio
                    return false;
                }
            }else{ //ya tiene un hijo izquiedo esta subRaiz, nos dirigimos hacia esa Rama,
                return agregar(subRaiz.getDirMemIzq(), elemento);
            }
        }else {
            //Igual a la informaciÃ³n de la subRaiz, no lo insertamos
            return false;
        }
    }

    public Object buscar(Object elemento){
        return buscar(raiz,elemento);
    }

    private Object buscar(NodoDoble subRaiz, Object elemento){
        if(subRaiz!=null){ //estamos en un nodo con algÃºn contenido
            if(Utilerias.compararObjetos(elemento,subRaiz.getDato())>0){ //elemento>subRaiz
                return buscar(subRaiz.getDirMemDer(), elemento);
            } else if(Utilerias.compararObjetos(elemento, subRaiz.getDato())<0){ //elemento<subRaiz
                return buscar(subRaiz.getDirMemIzq(), elemento);
            }else{ //elemento=subRaiz, lo encontrÃ©
                return subRaiz.getDato();
            }
        }else{ //estamos en un nodo que apunta a null
            //no estÃ¡ el elemento
            return null;
        }
    }



    /**
     * Metodo recursivo que nos ayuda a calcular un valor y despues si se encuentra dentro de el
     * arbol binario lo elimina
     * @param info
     */
    public void eliminarABB(Object info) {

        eliminarABB(raiz,null,info);
    }

    /**
     * metodo recurisvo que nos ayuda a eliminar en arbol binairo de busqueda
     * @param apnodo
     * @param anterior
     * @param info
     */
    private void eliminarABB(NodoDoble apnodo,NodoDoble anterior, Object info){
        if(apnodo!=null){
            if(Utilerias.compararObjetos(info,apnodo.getDato())==-1){
                eliminarABB(apnodo.getDirMemIzq(),apnodo,info);
            }else if(Utilerias.compararObjetos(info,apnodo.getDato())==1){
                eliminarABB(apnodo.getDirMemDer(),apnodo,info);
            }else if(apnodo.getDirMemIzq()!=null && apnodo.getDirMemDer()!= null){

                NodoDoble aux=apnodo.getDirMemIzq();
                NodoDoble aux1=aux;
                Boolean bo=false;
                while(aux.getDirMemDer()!=null){
                    aux1=aux;
                    aux=aux.getDirMemDer();
                    bo=true;
                }
                apnodo.setDato(aux.getDato());
                if(bo==true){
                    aux1.setDirMemDer(aux.getDirMemIzq());
                }else{
                    apnodo.setDirMemIzq(aux.getDirMemIzq());
                }
            }else{
                NodoDoble otro=apnodo;
                if(otro.getDirMemDer()==null){
                    if(otro.getDirMemIzq()!=null){
                        otro=apnodo.getDirMemIzq();
                        if(anterior!=null){
                            if(Utilerias.compararObjetos(info,anterior.getDato())==-1){
                                anterior.setDirMemIzq(otro);
                            }else{
                                anterior.setDirMemDer(otro);
                            }
                        }else{
                            raiz=otro;
                        }
                    }else if(anterior==null){

                        raiz=null;
                    }else if(Utilerias.compararObjetos(info,anterior.getDato())==-1){
                        anterior.setDirMemIzq(null);
                    }else{
                        anterior.setDirMemDer(null);
                    }
                }else if(otro.getDirMemDer()==null){
                    otro=apnodo.getDirMemDer();
                    if(anterior!=null){
                        if(Utilerias.compararObjetos(info,anterior.getDato())==-1){
                            anterior.setDirMemIzq(otro);
                        }else{
                            anterior.setDirMemDer(otro);
                        }
                    }else{
                        raiz=otro;
                    }
                }
            }
        }else{
            SalidaTerminal.consola("El Dato no existe: ");
        }
    }
}
