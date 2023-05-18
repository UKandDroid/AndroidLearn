package test.app.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import test.app.domain.model.ui.ScreenListItem
import test.app.domain.model.ui.EventItem
import javax.inject.Inject

class ChatSearcher @Inject constructor() {

  suspend fun search(list : List<ScreenListItem>, searchText: String) : Flow<List<ScreenListItem>> {
      list.map {
          if(it is EventItem) {
              it.highlight = it.text == searchText
         }
      }

    return flowOf( list)
 }
}