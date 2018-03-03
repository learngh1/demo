package com.example.api.impl.soap;

import com.example.api.impl.CustomFieldValueImpl;
import com.example.soap.RemoteCustomFieldValue;

/**
 * Created by user on 11.10.2017.
 */
public class SoapCustomFieldValue extends CustomFieldValueImpl {

    public SoapCustomFieldValue(String id, String key, String[] values) {
        super(id, key, values);
    }

    public SoapCustomFieldValue (RemoteCustomFieldValue delegate) {
        super(delegate.getCustomfieldId(), delegate.getKey(), delegate.getValues());
    }
}