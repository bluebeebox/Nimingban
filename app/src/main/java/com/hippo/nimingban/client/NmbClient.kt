/*
 * Copyright 2017 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.nimingban.client

/*
 * Created by Hippo on 6/4/2017.
 */

class NmbClient(private val engine: NmbEngine) {

  fun threads(forum: String, page: Int) = engine.threads(threadsUrl(forum, page))

  fun replies(id: String, page: Int) =
      engine.replies(repliesUrl(id, page))
          .map({
            val replies = it.replies.toMutableList()
            if (page == 0) {
              // It's the first, add thread itself to the header
              replies.add(0, it.toReply())
            }
            Pair(it, replies)
          })!!
}