/**
 * 
 */
package org.vikor.DataStructure;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author disfatum
 * @mail disfatum@yandex.ru
 * @github https://github.com/disfatum
 *
 */
public class VerifiableValue<T> {

    public enum VerificationState { UNVERIFIED, VERIFIED, ERROR }

    private final T value ;
    private final ObjectProperty<VerificationState> verificationState = new SimpleObjectProperty<>(VerificationState.UNVERIFIED) ;

    public VerifiableValue(T value) {
        this.value = value ;
    }

    public VerifiableValue(T value, VerificationState verificationState) {
        this(value);
        setVerificationState(verificationState);
    }



    public T getValue() {
        return value ;
    }

    public final ObjectProperty<VerificationState> verificationStateProperty() {
        return this.verificationState;
    }


    public final VerifiableValue.VerificationState getVerificationState() {
        return this.verificationStateProperty().get();
    }


    public final void setVerificationState(
            final VerifiableValue.VerificationState verificationState) {
        this.verificationStateProperty().set(verificationState);
    }


}
