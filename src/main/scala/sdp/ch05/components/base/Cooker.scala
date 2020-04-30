package sdp.ch05.components.base

import sdp.ch05.components.model.Food

trait Cooker {
  def cook(what: String): Food
}
