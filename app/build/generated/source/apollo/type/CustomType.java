package type;

import com.apollographql.apollo.api.ScalarType;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.net.URL;
import javax.annotation.Generated;

@Generated("Apollo GraphQL")
public enum CustomType implements ScalarType {
  URI {
    @Override
    public String typeName() {
      return "URI";
    }

    @Override
    public Class javaType() {
      return URL.class;
    }
  },

  ID {
    @Override
    public String typeName() {
      return "ID";
    }

    @Override
    public Class javaType() {
      return String.class;
    }
  }
}
