/*
 * Copyright 2013 Nebojsa Cvetkovic. All rights reserved.
 *
 * This file is part of JunglistIRC.
 *
 * JunglistIRC is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JunglistIRC is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JunglistIRC.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nebkat.junglist.irc.parser.irc.response;

import com.nebkat.junglist.irc.Channel;
import com.nebkat.junglist.irc.Session;
import com.nebkat.junglist.irc.events.irc.IRCEvent;
import com.nebkat.junglist.irc.events.irc.response.NamesEndEvent;
import com.nebkat.junglist.irc.events.irc.response.NamesListEvent;
import com.nebkat.junglist.irc.parser.ParseToken;
import com.nebkat.junglist.irc.parser.Parser;

public class NamesEventParser extends Parser {
    @Override
    public IRCEvent parse(long time, Session session, ParseToken token) {
        if (token.getParams().length < 2) {
            return null;
        }

        if (token.getCommand().equals(Parser.RESPONSE_NAMES_END)) {
            String channel = token.getParams()[1];
            Channel target = (Channel) session.getOrInitiateTarget(channel);
            return new NamesEndEvent(time, session, token.getRaw(), token.getSource(), target);
        } else if (token.getCommand().equals(Parser.RESPONSE_NAMES_LIST)) {
            if (token.getParams().length < 4) {
                return null;
            }
            String channel = token.getParams()[2];
            Channel target = (Channel) session.getOrInitiateTarget(channel);
            String[] list = token.getParams()[3].split(" ");
            return new NamesListEvent(time, session, token.getRaw(), token.getSource(), target, list);
        } else {
            return null;
        }
    }
}
