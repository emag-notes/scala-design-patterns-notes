package sdp.ch05.components

import sdp.ch05.components.base.{Cooker, RecipeFinder, Time}

class RobotRegistry extends TimeComponent with RecipeComponent with CookingComponent {
  override val time: Time           = new TimeImpl
  override val recipe: RecipeFinder = new RecipeFinderImpl
  override val cooker: Cooker       = new CookerImpl
}
