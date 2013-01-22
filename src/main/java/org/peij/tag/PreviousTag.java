package org.peij.tag;

/**
 * @author trgoofi
 *
 */
public class PreviousTag extends ClickableAwareTag {

  @Override
  protected int pageNumberToExpose() {
    return pagination.previous();
  }

  @Override
  protected boolean clickable() {
    return pagination.hasPreviousPage();
  }

  @Override
  protected String tagName() {
    return "prev";
  }
}
