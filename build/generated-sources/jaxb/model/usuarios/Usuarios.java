//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.11.24 a las 07:58:44 PM CET 
//


package model.usuarios;

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
 *         &lt;element name="usuario" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nombre_completo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nombre_usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "usuario"
})
@XmlRootElement(name = "usuarios")
public class Usuarios {

    protected List<Usuarios.Usuario> usuario;

    /**
     * Gets the value of the usuario property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usuario property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsuario().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Usuarios.Usuario }
     * 
     * 
     */
    public List<Usuarios.Usuario> getUsuario() {
        if (usuario == null) {
            usuario = new ArrayList<Usuarios.Usuario>();
        }
        return this.usuario;
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
     *         &lt;element name="nombre_completo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nombre_usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "nombreCompleto",
        "nombreUsuario",
        "password"
    })
    public static class Usuario {

        @XmlElement(name = "nombre_completo", required = true)
        protected String nombreCompleto;
        @XmlElement(name = "nombre_usuario", required = true)
        protected String nombreUsuario;
        @XmlElement(required = true)
        protected String password;

        /**
         * Obtiene el valor de la propiedad nombreCompleto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreCompleto() {
            return nombreCompleto;
        }

        /**
         * Define el valor de la propiedad nombreCompleto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreCompleto(String value) {
            this.nombreCompleto = value;
        }

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
         * Obtiene el valor de la propiedad password.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPassword() {
            return password;
        }

        /**
         * Define el valor de la propiedad password.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPassword(String value) {
            this.password = value;
        }

        @Override
        public String toString() {
            return "Usuario{" + "nombreCompleto=" + nombreCompleto + ", nombreUsuario=" + nombreUsuario + ", password=" + password + '}';
        }
        
        

    }

}
