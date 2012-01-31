/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.slf4j.impl;

import org.codehaus.plexus.logging.Logger;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class NullPlexusLogger implements Logger
{
   public static final NullPlexusLogger INSTANCE = new NullPlexusLogger();
   
   public void debug(String message)
   {
   }

   public void debug(String message, Throwable throwable)
   {
   }

   public boolean isDebugEnabled()
   {
      return false;
   }

   public void info(String message)
   {
   }

   public void info(String message, Throwable throwable)
   {
   }

   public boolean isInfoEnabled()
   {
      return false;
   }

   public void warn(String message)
   {
   }

   public void warn(String message, Throwable throwable)
   {
   }

   public boolean isWarnEnabled()
   {
      return false;
   }

   public void error(String message)
   {
   }

   public void error(String message, Throwable throwable)
   {
   }

   public boolean isErrorEnabled()
   {
      return false;
   }

   public void fatalError(String message)
   {
   }

   public void fatalError(String message, Throwable throwable)
   {
   }

   public boolean isFatalErrorEnabled()
   {
      return false;
   }

   public int getThreshold()
   {
      return 0;
   }

   public void setThreshold(int threshold)
   {
   }

   public Logger getChildLogger(String name)
   {
      return null;
   }

   public String getName()
   {
      return null;
   }

}
