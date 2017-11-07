import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Utils;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import javax.annotation.Generated;
import javax.annotation.Nonnull;

@Generated("Apollo GraphQL")
public final class ViewerQuery implements Query<ViewerQuery.Data, ViewerQuery.Data, Operation.Variables> {
  public static final String OPERATION_DEFINITION = "query Viewer {\n"
      + "  viewer {\n"
      + "    __typename\n"
      + "    login\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "Viewer";
    }
  };

  private final Operation.Variables variables;

  public ViewerQuery() {
    this.variables = Operation.EMPTY_VARIABLES;
  }

  @Override
  public String operationId() {
    return "74236b880dc924773f52cab62f0f58d65185d64e30665ad9a97894181d7fbef5";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public ViewerQuery.Data wrapData(ViewerQuery.Data data) {
    return data;
  }

  @Override
  public Operation.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<ViewerQuery.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  public static final class Builder {
    Builder() {
    }

    public ViewerQuery build() {
      return new ViewerQuery();
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("viewer", "viewer", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull Viewer viewer;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nonnull Viewer viewer) {
      this.viewer = Utils.checkNotNull(viewer, "viewer == null");
    }

    /**
     * The currently authenticated user.
     */
    public @Nonnull Viewer viewer() {
      return this.viewer;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], viewer.marshaller());
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "viewer=" + viewer
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return this.viewer.equals(that.viewer);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= viewer.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Viewer.Mapper viewerFieldMapper = new Viewer.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final Viewer viewer = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<Viewer>() {
          @Override
          public Viewer read(ResponseReader reader) {
            return viewerFieldMapper.map(reader);
          }
        });
        return new Data(viewer);
      }
    }
  }

  public static class Viewer {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("login", "login", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nonnull String login;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Viewer(@Nonnull String __typename, @Nonnull String login) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.login = Utils.checkNotNull(login, "login == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * The username used to login.
     */
    public @Nonnull String login() {
      return this.login;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], login);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Viewer{"
          + "__typename=" + __typename + ", "
          + "login=" + login
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Viewer) {
        Viewer that = (Viewer) o;
        return this.__typename.equals(that.__typename)
         && this.login.equals(that.login);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= login.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Viewer> {
      @Override
      public Viewer map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String login = reader.readString($responseFields[1]);
        return new Viewer(__typename, login);
      }
    }
  }
}
