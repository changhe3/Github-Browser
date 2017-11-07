package fragment;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Utils;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public class PageInfoQuery implements GraphqlFragment {
  static final ResponseField[] $responseFields = {
    ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forBoolean("hasNextPage", "hasNextPage", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forString("endCursor", "endCursor", null, true, Collections.<ResponseField.Condition>emptyList())
  };

  public static final String FRAGMENT_DEFINITION = "fragment PageInfoQuery on PageInfo {\n"
      + "  __typename\n"
      + "  hasNextPage\n"
      + "  endCursor\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "PageInfo"));

  final @Nonnull String __typename;

  final boolean hasNextPage;

  final @Nullable String endCursor;

  private volatile String $toString;

  private volatile int $hashCode;

  private volatile boolean $hashCodeMemoized;

  public PageInfoQuery(@Nonnull String __typename, boolean hasNextPage,
      @Nullable String endCursor) {
    this.__typename = Utils.checkNotNull(__typename, "__typename == null");
    this.hasNextPage = hasNextPage;
    this.endCursor = endCursor;
  }

  public @Nonnull String __typename() {
    return this.__typename;
  }

  /**
   * When paginating forwards, are there more items?
   */
  public boolean hasNextPage() {
    return this.hasNextPage;
  }

  /**
   * When paginating forwards, the cursor to continue.
   */
  public @Nullable String endCursor() {
    return this.endCursor;
  }

  public ResponseFieldMarshaller marshaller() {
    return new ResponseFieldMarshaller() {
      @Override
      public void marshal(ResponseWriter writer) {
        writer.writeString($responseFields[0], __typename);
        writer.writeBoolean($responseFields[1], hasNextPage);
        writer.writeString($responseFields[2], endCursor);
      }
    };
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "PageInfoQuery{"
        + "__typename=" + __typename + ", "
        + "hasNextPage=" + hasNextPage + ", "
        + "endCursor=" + endCursor
        + "}";
    }
    return $toString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PageInfoQuery) {
      PageInfoQuery that = (PageInfoQuery) o;
      return this.__typename.equals(that.__typename)
       && this.hasNextPage == that.hasNextPage
       && ((this.endCursor == null) ? (that.endCursor == null) : this.endCursor.equals(that.endCursor));
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
      h ^= Boolean.valueOf(hasNextPage).hashCode();
      h *= 1000003;
      h ^= (endCursor == null) ? 0 : endCursor.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<PageInfoQuery> {
    @Override
    public PageInfoQuery map(ResponseReader reader) {
      final String __typename = reader.readString($responseFields[0]);
      final boolean hasNextPage = reader.readBoolean($responseFields[1]);
      final String endCursor = reader.readString($responseFields[2]);
      return new PageInfoQuery(__typename, hasNextPage, endCursor);
    }
  }
}
