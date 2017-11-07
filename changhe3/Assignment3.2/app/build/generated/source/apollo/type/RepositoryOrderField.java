package type;

import javax.annotation.Generated;

/**
 * Properties by which repository connections can be ordered.
 */
@Generated("Apollo GraphQL")
public enum RepositoryOrderField {
  /**
   * Order repositories by creation time
   */
  CREATED_AT,

  /**
   * Order repositories by update time
   */
  UPDATED_AT,

  /**
   * Order repositories by push time
   */
  PUSHED_AT,

  /**
   * Order repositories by name
   */
  NAME,

  /**
   * Order repositories by number of stargazers
   */
  STARGAZERS
}
