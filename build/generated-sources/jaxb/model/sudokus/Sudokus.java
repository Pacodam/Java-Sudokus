//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.11.24 a las 07:57:26 PM CET 
//


package model.sudokus;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="sudoku" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="problem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="solved" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "sudoku"
})
@XmlRootElement(name = "sudokus")
public class Sudokus {

    protected List<Sudokus.Sudoku> sudoku;

    /**
     * Gets the value of the sudoku property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sudoku property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSudoku().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sudokus.Sudoku }
     * 
     * 
     */
    public List<Sudokus.Sudoku> getSudoku() {
        if (sudoku == null) {
            sudoku = new ArrayList<Sudokus.Sudoku>();
        }
        return this.sudoku;
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
     *         &lt;element name="problem" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="solved" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="level" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "problem",
        "solved"
    })
    public static class Sudoku {

        @XmlElement(required = true)
        protected String problem;
        @XmlElement(required = true)
        protected String solved;
        @XmlAttribute(name = "level")
        protected BigInteger level;
        @XmlAttribute(name = "description")
        protected String description;

        /**
         * Obtiene el valor de la propiedad problem.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProblem() {
            return problem;
        }

        /**
         * Define el valor de la propiedad problem.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProblem(String value) {
            this.problem = value;
        }

        /**
         * Obtiene el valor de la propiedad solved.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSolved() {
            return solved;
        }

        /**
         * Define el valor de la propiedad solved.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSolved(String value) {
            this.solved = value;
        }

        /**
         * Obtiene el valor de la propiedad level.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getLevel() {
            return level;
        }

        /**
         * Define el valor de la propiedad level.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setLevel(BigInteger value) {
            this.level = value;
        }

        /**
         * Obtiene el valor de la propiedad description.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Define el valor de la propiedad description.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        @Override
        public String toString() {
            return "Sudoku{" + "description=" + description + ",level=" + level + ", \nproblem=" + problem + ", \nsolved=" + solved + '}';
        }
        
        

    }

}
