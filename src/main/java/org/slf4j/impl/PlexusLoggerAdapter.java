/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.slf4j.impl;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/**
 * A wrapper over {@link org.codehaus.plexus.logging.Logger} in conforming to the {@link Logger} interface.
 * 
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class PlexusLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger
{
   private static final long serialVersionUID = 1L;

   private transient org.codehaus.plexus.logging.Logger logger;

   public PlexusLoggerAdapter(String name)
   {
      this.name = name;
   }

   public void setLogger(org.codehaus.plexus.logging.Logger logger)
   {
      this.logger = logger;
   }

   private org.codehaus.plexus.logging.Logger getLogger()
   {
      return logger;
   }

   public boolean isTraceEnabled()
   {
      return isDebugEnabled();
   }

   public void trace(String msg)
   {
      debug(msg);
   }

   public void trace(String format, Object arg)
   {
      debug(format, arg);
   }

   public void trace(String format, Object arg1, Object arg2)
   {
      debug(format, arg1, arg2);
   }

   public void trace(String format, Object[] argArray)
   {
      debug(format, argArray);
   }

   public void trace(String msg, Throwable t)
   {
      debug(msg, t);
   }

   public boolean isDebugEnabled()
   {
      return getLogger().isDebugEnabled();
   }

   public void debug(String msg)
   {
      getLogger().debug(msg);
   }

   public void debug(String format, Object arg)
   {
      if (isDebugEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg);
         getLogger().debug(ft.getMessage(), ft.getThrowable());
      }
   }

   public void debug(String format, Object arg1, Object arg2)
   {
      if (isDebugEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
         getLogger().debug(ft.getMessage(), ft.getThrowable());
      }
   }

   public void debug(String format, Object[] argArray)
   {
      if (isDebugEnabled())
      {
         FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
         getLogger().debug(ft.getMessage(), ft.getThrowable());
      }
   }

   public void debug(String msg, Throwable t)
   {
      getLogger().debug(msg, t);
   }

   public boolean isInfoEnabled()
   {
      return getLogger().isInfoEnabled();
   }

   public void info(String msg)
   {
      getLogger().info(msg);
   }

   public void info(String format, Object arg)
   {
      if (isInfoEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg);
         getLogger().info(ft.getMessage(), ft.getThrowable());
      }
   }

   public void info(String format, Object arg1, Object arg2)
   {
      if (isInfoEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
         getLogger().info(ft.getMessage(), ft.getThrowable());
      }
   }

   public void info(String format, Object[] argArray)
   {
      if (isInfoEnabled())
      {
         FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
         getLogger().info(ft.getMessage(), ft.getThrowable());
      }
   }

   public void info(String msg, Throwable t)
   {
      getLogger().info(msg, t);
   }

   public boolean isWarnEnabled()
   {
      return getLogger().isWarnEnabled();
   }

   public void warn(String msg)
   {
      getLogger().warn(msg);
   }

   public void warn(String format, Object arg)
   {
      if (isWarnEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg);
         getLogger().warn(ft.getMessage(), ft.getThrowable());
      }
   }

   public void warn(String format, Object arg1, Object arg2)
   {
      if (isWarnEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
         getLogger().warn(ft.getMessage(), ft.getThrowable());
      }
   }

   public void warn(String format, Object[] argArray)
   {
      if (isWarnEnabled())
      {
         FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
         getLogger().warn(ft.getMessage(), ft.getThrowable());
      }
   }

   public void warn(String msg, Throwable t)
   {
      getLogger().warn(msg, t);
   }

   public boolean isErrorEnabled()
   {
      return getLogger().isErrorEnabled();
   }

   public void error(String msg)
   {
      getLogger().error(msg);
   }

   public void error(String format, Object arg)
   {
      if (isErrorEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg);
         getLogger().error(ft.getMessage(), ft.getThrowable());
      }
   }

   public void error(String format, Object arg1, Object arg2)
   {
      if (isErrorEnabled())
      {
         FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
         getLogger().error(ft.getMessage(), ft.getThrowable());
      }
   }

   public void error(String format, Object[] argArray)
   {
      if (isErrorEnabled())
      {
         FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
         getLogger().error(ft.getMessage(), ft.getThrowable());
      }
   }

   public void error(String msg, Throwable t)
   {
      getLogger().error(msg, t);
   }

   public void log(Marker marker, String callerFQCN, int level, String msg, Object[] argArray, Throwable t)
   {
      switch (level)
      {
         case LocationAwareLogger.TRACE_INT :
            trace(msg, t);
            break;
         case LocationAwareLogger.DEBUG_INT :
            debug(msg, t);
            break;
         case LocationAwareLogger.INFO_INT :
            info(msg, t);
            break;
         case LocationAwareLogger.WARN_INT :
            warn(msg, t);
            break;
         case LocationAwareLogger.ERROR_INT :
            error(msg, t);
            break;
         default :
            throw new IllegalStateException("Level number " + level + " is not recognized.");
      }
   }

}
