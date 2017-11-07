import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public final class AddStarMutation implements Mutation<AddStarMutation.Data, AddStarMutation.Data, AddStarMutation.Variables> {
  public static final String OPERATION_DEFINITION = "mutation addStar($clientId: String!, $starrableId: ID!) {\n"
      + "  addStar(input: {clientMutationId: $clientId, starrableId: $starrableId}) {\n"
      + "    __typename\n"
      + "    starrable {\n"
      + "      __typename\n"
      + "      viewerHasStarred\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "addStar";
    }
  };

  private final AddStarMutation.Variables variables;

  public AddStarMutation(@Nonnull String clientId, @Nonnull String starrableId) {
    Utils.checkNotNull(clientId, "clientId == null");
    Utils.checkNotNull(starrableId, "starrableId == null");
    variables = new AddStarMutation.Variables(clientId, starrableId);
  }

  @Override
  public String operationId() {
    return "27e8cb08fb1932e5865e4a4c10471b730be524bffe497ed4c19ccbfcd3d510dc";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public AddStarMutation.Data wrapData(AddStarMutation.Data data) {
    return data;
  }

  @Override
  public AddStarMutation.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<AddStarMutation.Data> responseFieldMapper() {
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
    private @Nonnull String clientId;

    private @Nonnull String starrableId;

    Builder() {
    }

    public Builder clientId(@Nonnull String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder starrableId(@Nonnull String starrableId) {
      this.starrableId = starrableId;
      return this;
    }

    public AddStarMutation build() {
      Utils.checkNotNull(clientId, "clientId == null");
      Utils.checkNotNull(starrableId, "starrableId == null");
      return new AddStarMutation(clientId, starrableId);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @Nonnull String clientId;

    private final @Nonnull String starrableId;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@Nonnull String clientId, @Nonnull String starrableId) {
      this.clientId = clientId;
      this.starrableId = starrableId;
      this.valueMap.put("clientId", clientId);
      this.valueMap.put("starrableId", starrableId);
    }

    public @Nonnull String clientId() {
      return clientId;
    }

    public @Nonnull String starrableId() {
      return starrableId;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public InputFieldMarshaller marshaller() {
      return new InputFieldMarshaller() {
        @Override
        public void marshal(InputFieldWriter writer) throws IOException {
          writer.writeString("clientId", clientId);
          writer.writeCustom("starrableId", type.CustomType.ID, starrableId);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("addStar", "addStar", new UnmodifiableMapBuilder<String, Object>(1)
        .put("input", new UnmodifiableMapBuilder<String, Object>(2)
          .put("clientMutationId", new UnmodifiableMapBuilder<String, Object>(2)
            .put("kind", "Variable")
            .put("variableName", "clientId")
          .build())
          .put("starrableId", new UnmodifiableMapBuilder<String, Object>(2)
            .put("kind", "Variable")
            .put("variableName", "starrableId")
          .build())
        .build())
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable AddStar addStar;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable AddStar addStar) {
      this.addStar = addStar;
    }

    /**
     * Adds a star to a Starrable.
     */
    public @Nullable AddStar addStar() {
      return this.addStar;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], addStar != null ? addStar.marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "addStar=" + addStar
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
        return ((this.addStar == null) ? (that.addStar == null) : this.addStar.equals(that.addStar));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (addStar == null) ? 0 : addStar.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final AddStar.Mapper addStarFieldMapper = new AddStar.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final AddStar addStar = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<AddStar>() {
          @Override
          public AddStar read(ResponseReader reader) {
            return addStarFieldMapper.map(reader);
          }
        });
        return new Data(addStar);
      }
    }
  }

  public static class AddStar {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("starrable", "starrable", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nonnull Starrable starrable;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public AddStar(@Nonnull String __typename, @Nonnull Starrable starrable) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.starrable = Utils.checkNotNull(starrable, "starrable == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * The starrable.
     */
    public @Nonnull Starrable starrable() {
      return this.starrable;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeObject($responseFields[1], starrable.marshaller());
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AddStar{"
          + "__typename=" + __typename + ", "
          + "starrable=" + starrable
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AddStar) {
        AddStar that = (AddStar) o;
        return this.__typename.equals(that.__typename)
         && this.starrable.equals(that.starrable);
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
        h ^= starrable.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AddStar> {
      final Starrable.Mapper starrableFieldMapper = new Starrable.Mapper();

      @Override
      public AddStar map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Starrable starrable = reader.readObject($responseFields[1], new ResponseReader.ObjectReader<Starrable>() {
          @Override
          public Starrable read(ResponseReader reader) {
            return starrableFieldMapper.map(reader);
          }
        });
        return new AddStar(__typename, starrable);
      }
    }
  }

  public static class Starrable {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forBoolean("viewerHasStarred", "viewerHasStarred", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final boolean viewerHasStarred;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Starrable(@Nonnull String __typename, boolean viewerHasStarred) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.viewerHasStarred = viewerHasStarred;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * Returns a boolean indicating whether the viewing user has starred this starrable.
     */
    public boolean viewerHasStarred() {
      return this.viewerHasStarred;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeBoolean($responseFields[1], viewerHasStarred);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Starrable{"
          + "__typename=" + __typename + ", "
          + "viewerHasStarred=" + viewerHasStarred
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Starrable) {
        Starrable that = (Starrable) o;
        return this.__typename.equals(that.__typename)
         && this.viewerHasStarred == that.viewerHasStarred;
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
        h ^= Boolean.valueOf(viewerHasStarred).hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Starrable> {
      @Override
      public Starrable map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final boolean viewerHasStarred = reader.readBoolean($responseFields[1]);
        return new Starrable(__typename, viewerHasStarred);
      }
    }
  }
}
