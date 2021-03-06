/*
 * Copyright 2013, 2014 Megion Research & Development GmbH
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

package com.mycelium.lt.location;

import java.util.ArrayList;
import java.util.Collection;

public class Geocode {

   private static final String COUNTRY = "country";

   public Collection<String> types = new ArrayList<String>();
   public String formattedAddress;
   public Collection<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
   public Collection<String> postcodeLocalities = new ArrayList<String>();
   public Geometry geometry;
   public boolean partialMatch;

   public double getLatitude() {
      return geometry.location.lat;
   }

   public double getLongitude() {
      return geometry.location.lng;
   }

   /**
    * Determine the country code. If no country code was found the empty string
    * is returned.
    */
   public String getCountryCode() {
      if (addressComponents == null) {
         return "";
      }
      for (AddressComponent comp : addressComponents) {
         if (comp.types == null) {
            continue;
         }
         for (String type : comp.types) {
            if (COUNTRY.equals(type)) {
               if (comp.shortName != null && comp.shortName.length() > 0) {
                  return comp.shortName;
               }
            }
         }
      }
      return "";
   }

}
