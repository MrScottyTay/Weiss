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

/**
 *
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class RouterMessage extends DecoratedMessage
{
    private String origin;
    
    
    public RouterMessage(String f, String t, String m, Message c, String o)
    {
        super(f, t, m, c);
        origin = o;
    }
    
    /** Constructor for a RouterMessage object. The object is used for Router to Router
     * communication, allowing for use in a linked list.
     *
     * @param f String variable detailing where the message is from.
     * @param t String variable detailing where the message is going.
     * @param c The Message object to be wrapped.
     * @param o The Router the message was created in originally.
     */
    public RouterMessage(String f, String t, String m, Message c)
    {
        super(f, t, m, c);
    }
    
    
    //--------------------------------------------------------------------------
    //Getters
    //--------------------------------------------------------------------------
    /**
     * Method to get the origin of the message.
     * @return String of the origin variable.
     */
    public String getOrigin()
    {
        return origin;
    }
    
}
