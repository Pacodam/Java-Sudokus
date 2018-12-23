//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.11.24 a las 07:58:07 PM CET 
//


package model.historials;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="resuelto" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nombre_usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sudoku_num" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tiempo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "resuelto"
})
@XmlRootElement(name = "historial")
public class Historial {

    protected List<Historial.Resuelto> resuelto;

    /**
     * Gets the value of the resuelto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resuelto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResuelto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Historial.Resuelto }
     * 
     * 
     */
    public List<Historial.Resuelto> getResuelto() {
        if (resuelto == null) {
            resuelto = new ArrayList<Historial.Resuelto>();
        }
        return this.resuelto;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="nombre_usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sudoku_num" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tiempo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nombreUsuario",
        "sudokuNum",
        "tiempo"
    })
    public static class Resuelto {

        @XmlElement(name = "nombre_usuario", required = true)
        protected String nombreUsuario;
        @XmlElement(name = "sudoku_num", required = true)
        protected String sudokuNum;
        @XmlElement(required = true)
        protected String tiempo;

        /**
         * Obtiene el valor de la propiedad nombreUsuario.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreUsuario() {
            return nombreUsuario;
        }

        /**
         * Define el valor de la propiedad nombreUsuario.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreUsuario(String value) {
            this.nombreUsuario = value;
        }

        /**
         * Obtiene el valor de la propiedad sudokuNum.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSudokuNum() {
            return sudokuNum;
        }

        /**
         * Define el valor de la propiedad sudokuNum.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSudokuNum(String value) {
            this.sudokuNum = value;
        }

        /**
         * Obtiene el valor de la propiedad tiempo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTiempo() {
            return tiempo;
        }

        /**
         * Define el valor de la propiedad tiempo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTiempo(String value) {
            this.tiempo = value;
        }

    }

}
