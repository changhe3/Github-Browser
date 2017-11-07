package type;

import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.internal.Utils;
import java.io.IOException;
import java.lang.Override;
import javax.annotation.Generated;
import javax.annotation.Nonnull;

@Generated("Apollo GraphQL")
public final class RepositoryOrder {
  private final @Nonnull RepositoryOrderField field;

  private final @Nonnull OrderDirection direction;

  RepositoryOrder(@Nonnull RepositoryOrderField field, @Nonnull OrderDirection direction) {
    this.field = field;
    this.direction = direction;
  }

  /**
   * The field to order repositories by.
   */
  public @Nonnull RepositoryOrderField field() {
    return this.field;
  }

  /**
   * The ordering direction.
   */
  public @Nonnull OrderDirection direction() {
    return this.direction;
  }

  public static Builder builder() {
    return new Builder();
  }

  public InputFieldMarshaller marshaller() {
    return new InputFieldMarshaller() {
      @Override
      public void marshal(InputFieldWriter writer) throws IOException {
        writer.writeString("field", field.name());
        writer.writeString("direction", direction.name());
      }
    };
  }

  public static final class Builder {
    private @Nonnull RepositoryOrderField field;

    private @Nonnull OrderDirection direction;

    Builder() {
    }

    /**
     * The field to order repositories by.
     */
    public Builder field(@Nonnull RepositoryOrderField field) {
      this.field = field;
      return this;
    }

    /**
     * The ordering direction.
     */
    public Builder direction(@Nonnull OrderDirection direction) {
      this.direction = direction;
      return this;
    }

    public RepositoryOrder build() {
      Utils.checkNotNull(field, "field == null");
      Utils.checkNotNull(direction, "direction == null");
      return new RepositoryOrder(field, direction);
    }
  }
}
