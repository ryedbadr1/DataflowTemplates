/*
 * Copyright (C) 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.teleport.v2.templates.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** Schema object to store mapping information as per the Schema file generated by HarbourBridge. */
public class Schema implements Serializable {

  /** Maps the HarbourBridge table ID to the Source schema details. */
  private final Map<String, SourceSchema> srcSchema;

  public Schema() {
    this.srcSchema = new HashMap<String, SourceSchema>();
  }

  public Schema(Map<String, SourceSchema> srcSchema) {
    this.srcSchema = srcSchema;
  }

  public Map<String, SourceSchema> getSourceSchema() {
    return srcSchema;
  }

  public String toString() {
    return String.format("{ 'SourceSchema': '%s' }", srcSchema);
  }

  public SourceSchema getSourceSchemaForTable(String tableName) {
    for (Map.Entry<String, SourceSchema> entry : srcSchema.entrySet()) {
      SourceSchema temp = entry.getValue();
      if (temp.getName().equals(tableName)) {
        return temp;
      }
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Schema)) {
      return false;
    }
    final Schema other = (Schema) o;
    return this.srcSchema.equals(other.srcSchema);
  }
}
