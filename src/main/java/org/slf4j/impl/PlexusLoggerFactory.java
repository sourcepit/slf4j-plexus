/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.slf4j.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.plexus.logging.LoggerManager;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class PlexusLoggerFactory implements ILoggerFactory
{
   private final Map<String, PlexusLoggerAdapter> loggerMap = new HashMap<String, PlexusLoggerAdapter>();

   private LoggerManager loggerManager;

   public synchronized void setLoggerManager(LoggerManager loggerManager)
   {
      if (this.loggerManager == loggerManager)
      {
         return;
      }
      
      final LoggerManager oldLoggerManager = this.loggerManager;
      this.loggerManager = loggerManager;
      
      for (Entry<String, PlexusLoggerAdapter> entry : loggerMap.entrySet())
      {
         final String name = entry.getKey();
         final PlexusLoggerAdapter logger = entry.getValue();

         if (oldLoggerManager != null)
         {
            logger.setLogger(NullPlexusLogger.INSTANCE);
            oldLoggerManager.returnComponentLogger(name);
         }

         if (loggerManager != null)
         {
            logger.setLogger(loggerManager.getLoggerForComponent(name));
         }
      }
   }

   public synchronized Logger getLogger(String name)
   {
      PlexusLoggerAdapter logger = loggerMap.get(name);
      if (logger == null)
      {
         logger = new PlexusLoggerAdapter(name);
         logger
            .setLogger(loggerManager == null ? NullPlexusLogger.INSTANCE : loggerManager.getLoggerForComponent(name));
         loggerMap.put(name, logger);
      }
      return logger;
   }
}
