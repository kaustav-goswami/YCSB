/**
 * Copyright (c) 2010-2016 Yahoo! Inc., 2017 YCSB contributors All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */

package site.ycsb.measurements;

import site.ycsb.measurements.exporter.MeasurementsExporter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Count-only measurement for gem5 / simulation runs.
 * Latency histograms are omitted; m5 stats cover timing in the ROI.
 */
public class OneMeasurementCount extends OneMeasurement {

  private final AtomicLong operations = new AtomicLong();

  public OneMeasurementCount(String name) {
    super(name);
  }

  @Override
  public void measure(int latency) {
    operations.incrementAndGet();
  }

  @Override
  public void exportMeasurements(MeasurementsExporter exporter) throws IOException {
    exporter.write(getName(), "Operations", operations.get());
    exportStatusCounts(exporter);
  }

  @Override
  public String getSummary() {
    return "[" + getName() + ": Count=" + operations.get() + "]";
  }
}
