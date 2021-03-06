/* 
 * Copyright (C) 2017 Adam Young
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package weiss.core.message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class detailing the basic message structure to be used within Weiss. Uses
 * String variables to provide easier extension with other systems.
 * <p>
 * To be used in classes implementing the {@link weiss.core.agent MetaAgent}
 * abstract class.
 *
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class Message
{

    private final String from;
    private final String to;
    private final String timestamp;

    /**
     * Constructor to create a generic message object.
     *
     * @param from String of message sender.
     * @param to String of message receiver.
     */
    public Message(String from, String to)
    {
        this.from = from;
        this.to = to;

        //Setting the time of creation
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = Calendar.getInstance().getTime();
        timestamp = df.format(now);
    }

    //--------------------------------------------------------------------------
    //GETTERS
    /**
     * Getter for the from variable.
     *
     * @return String of from variable.
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * Getter for the to variable.
     *
     * @return String of to variable.
     */
    public String getTo()
    {
        return to;
    }

    /**
     * Getter for the time variable.
     *
     * @return String of time variable.
     */
    public String getTime()
    {
        return timestamp;
    }

    @Override
    public String toString()
    {
        return "\nFrom: " + from + "\nTo: " + to + "\nTime Sent: " + timestamp;
    }
}
