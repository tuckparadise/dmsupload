package com.rhb.api.utilities;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class MyNamespaceMapper extends NamespacePrefixMapper {

    private static final String FOO_PREFIX = "tem"; // DEFAULT NAMESPACE
    private static final String FOO_URI = "http://tempuri.org/";



    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        if(FOO_URI.equals(namespaceUri)) {
            return FOO_PREFIX;
        }
        return suggestion;
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[] { FOO_URI };
    }

}
