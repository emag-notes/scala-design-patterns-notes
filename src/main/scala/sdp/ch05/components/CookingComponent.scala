package sdp.ch05.components

import sdp.ch05.components.base.Cooker
import sdp.ch05.components.model.Food

trait CookingComponent {
  this: RecipeComponent =>

  val cooker: Cooker

  class CookerImpl extends Cooker {
    override def cook(what: String): Food = {
      val recipeText = recipe.findRecipe(what)
      Food(s"We just cooked $what using the following recipe: $recipeText")
    }
  }
}
