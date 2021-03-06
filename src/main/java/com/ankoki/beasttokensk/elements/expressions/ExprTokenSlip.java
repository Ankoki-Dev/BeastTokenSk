package com.ankoki.beasttokensk.elements.expressions;


import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mraxetv.beasttokens.BeastTokensAPI;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Token Slips")
@Description("Gets a token slip")
@Examples({"command /tokenslip" +
        "\ttrigger",
        "\t\tgive player a beast token slip worth 5000 beast tokens"})
@Since("1.0")
@RequiredPlugins("BeastTokens")
public class ExprTokenSlip extends SimpleExpression<ItemType> {

    static {
        Skript.registerExpression(ExprTokenSlip.class, ItemType.class, ExpressionType.SIMPLE, "a [beast[ ]]token slip worth %number% [[beast[ ]]tokens]");
    }

    private Expression<Number> tokens;

    @Nullable
    @Override
    protected ItemType[] get(Event e) {
        return new ItemType[]{new ItemType(BeastTokensAPI.getTokensManager().getTokenNote("Token Note",
                tokens.getSingle(e).doubleValue(), 1, false))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemType> getReturnType() {
        return ItemType.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "a token slip";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        tokens = (Expression<Number>) exprs[0];
        return true;
    }
}
