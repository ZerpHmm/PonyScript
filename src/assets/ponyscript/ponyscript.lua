__TARGET__ = nil -- userdata
__SERVER__ = nil -- userdata
__WORLD__ = nil -- userdata

-- metatable how override __index and __newindex
-- getters are "get_<key>" -> must be implemented in java class as "public Object get_<key>()" or in lua as "function get_<key>(table)"
-- setters are "set_<key>" -> must be implemented in java class as "public void set_<key>(Object newvalue)" or in lua as "function set_<key>(table, value)"
__GETTERS_METATABLE__ = {
    __index = function(javadata, key)
        local getter = javadata["get_" .. key]
        
        if getter then
            return getter(javadata)
        end
    end,
    
    __newindex = function(javadata, key, value)
        local setter = javadata["set_" .. key]
        
        if setter then
            setter(javadata, value)
        end
    end
}

---------------------------------------------------------------

function chat(msg)
	__TARGET__:chat(msg)
end

function blockat(x, y, z)    
    assert(x and y and z, "coords can't be null")
    
    block = __WORLD__:getBlockAt(x, y, z)
    setmetatable(block, __GETTERS_METATABLE__)
    
    return block
end

this = {
    block = function()
        return blockat(__TARGET__.x, __TARGET__.y, __TARGET__.z)
    end
}

---------------------------------------------------------------

function rand(...)
    return math.random(...)
end