/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mike Merola
 * Section: 01
 * Date: 4/19/24
 * Time: 2:09 PM
 *
 * Project: csci205_final_project
 * Package: org.MMMJ.FXML
 * Class: Observable2D
 *
 * Description:
 *
 * ****************************************
 */
package org.MMMJ.FXML;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

public class ObservableArray2D<T> implements Observable{
    /** A {@link SimpleObjectProperty}**/
    private final ObjectProperty<T[][]> arrayProperty = new SimpleObjectProperty<>();

    /**
     * A constructor for the ObservableArray2D class
     * @param array - a 2D array of T objects
     */
    public ObservableArray2D(T[][] array) {
        arrayProperty.set(array);
        set(array);
    }

    /**
     * Returns the arrayProperty
     * @return arrayProperty
     */
    public ObjectProperty<T[][]> arrayProperty() {
        return arrayProperty;
    }

    /**
     * A getter method that returns the arrayProperty
     * @return arrayProperty
     */
    public T[][] get() {
        return arrayProperty.get();
    }

    /**
     * A setter method that sets the arrayProperty to a new array
     * @param array - the new array you want the arrayProperty to be set to
     */
    public void set(T[][] array) {
        arrayProperty.set(array);
    }

    /**
     * Adds a listener to the array property to check if an event occurs
     * @param listener - the type of listener
     */
    @Override
    public void addListener(InvalidationListener listener) {
        arrayProperty.addListener(listener);
    }

    /**
     * removes a listener from the array property
     * @param listener - the type of listener
     */
    @Override
    public void removeListener(InvalidationListener listener) {
        arrayProperty.removeListener(listener);
    }

}
