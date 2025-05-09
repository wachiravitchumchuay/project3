//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.05.04 at 11:15:13 PM ICT 
//


package demo.project3.schema;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="restaurants" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="confidence" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="carbohydrates" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="restaurant_nationality" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="protein" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="fat" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="restaurant_type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="food_type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="clean_max_budget" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="clean_min_budget" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="restaurant_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "restaurants"
})
@XmlRootElement(name = "getRestaurantRecommendationResponse")
public class GetRestaurantRecommendationResponse {

    protected List<GetRestaurantRecommendationResponse.Restaurants> restaurants;

    /**
     * Gets the value of the restaurants property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the restaurants property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRestaurants().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetRestaurantRecommendationResponse.Restaurants }
     * 
     * 
     */
    public List<GetRestaurantRecommendationResponse.Restaurants> getRestaurants() {
        if (restaurants == null) {
            restaurants = new ArrayList<GetRestaurantRecommendationResponse.Restaurants>();
        }
        return this.restaurants;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="confidence" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="carbohydrates" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="restaurant_nationality" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="protein" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="fat" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="restaurant_type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="food_type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="clean_max_budget" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="clean_min_budget" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="restaurant_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "confidence",
        "carbohydrates",
        "restaurantNationality",
        "district",
        "protein",
        "fat",
        "restaurantType",
        "foodType",
        "cleanMaxBudget",
        "cleanMinBudget",
        "restaurantName"
    })
    public static class Restaurants {

        @XmlElement(required = true)
        protected String confidence;
        @XmlElement(required = true)
        protected String carbohydrates;
        @XmlElement(name = "restaurant_nationality", required = true)
        protected String restaurantNationality;
        @XmlElement(required = true)
        protected String district;
        @XmlElement(required = true)
        protected String protein;
        @XmlElement(required = true)
        protected String fat;
        @XmlElement(name = "restaurant_type", required = true)
        protected String restaurantType;
        @XmlElement(name = "food_type", required = true)
        protected String foodType;
        @XmlElement(name = "clean_max_budget", required = true)
        protected String cleanMaxBudget;
        @XmlElement(name = "clean_min_budget", required = true)
        protected String cleanMinBudget;
        @XmlElement(name = "restaurant_name", required = true)
        protected String restaurantName;

        /**
         * Gets the value of the confidence property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConfidence() {
            return confidence;
        }

        /**
         * Sets the value of the confidence property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConfidence(String value) {
            this.confidence = value;
        }

        /**
         * Gets the value of the carbohydrates property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCarbohydrates() {
            return carbohydrates;
        }

        /**
         * Sets the value of the carbohydrates property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCarbohydrates(String value) {
            this.carbohydrates = value;
        }

        /**
         * Gets the value of the restaurantNationality property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRestaurantNationality() {
            return restaurantNationality;
        }

        /**
         * Sets the value of the restaurantNationality property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRestaurantNationality(String value) {
            this.restaurantNationality = value;
        }

        /**
         * Gets the value of the district property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDistrict() {
            return district;
        }

        /**
         * Sets the value of the district property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDistrict(String value) {
            this.district = value;
        }

        /**
         * Gets the value of the protein property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProtein() {
            return protein;
        }

        /**
         * Sets the value of the protein property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProtein(String value) {
            this.protein = value;
        }

        /**
         * Gets the value of the fat property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFat() {
            return fat;
        }

        /**
         * Sets the value of the fat property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFat(String value) {
            this.fat = value;
        }

        /**
         * Gets the value of the restaurantType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRestaurantType() {
            return restaurantType;
        }

        /**
         * Sets the value of the restaurantType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRestaurantType(String value) {
            this.restaurantType = value;
        }

        /**
         * Gets the value of the foodType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFoodType() {
            return foodType;
        }

        /**
         * Sets the value of the foodType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFoodType(String value) {
            this.foodType = value;
        }

        /**
         * Gets the value of the cleanMaxBudget property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCleanMaxBudget() {
            return cleanMaxBudget;
        }

        /**
         * Sets the value of the cleanMaxBudget property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCleanMaxBudget(String value) {
            this.cleanMaxBudget = value;
        }

        /**
         * Gets the value of the cleanMinBudget property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCleanMinBudget() {
            return cleanMinBudget;
        }

        /**
         * Sets the value of the cleanMinBudget property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCleanMinBudget(String value) {
            this.cleanMinBudget = value;
        }

        /**
         * Gets the value of the restaurantName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRestaurantName() {
            return restaurantName;
        }

        /**
         * Sets the value of the restaurantName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRestaurantName(String value) {
            this.restaurantName = value;
        }

    }

}
