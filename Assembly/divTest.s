	.section	__TEXT,__text,regular,pure_instructions

_main:                                  ## @main
	pushq	%rbp
	movq	%rsp, %rbp

	subq	$16, %rsp
	movl	$12, -4(%rbp)
	movl	$32, -8(%rbp)

	movl	-4(%rbp), %eax
	cltd
	idivl	-8(%rbp)
	movl	%eax, -12(%rbp)

	movl	-12(%rbp), %esi
	movb	$0, %al
	callq	_printf
	xorl	%esi, %esi
	movl	%eax, -16(%rbp)         ## 4-byte Spill
	movl	%esi, %eax
	addq	$16, %rsp
	popq	%rbp
	retq
	.cfi_endproc

	.section	__TEXT,__cstring,cstring_literals
L_.str:                                 ## @.str
	.asciz	"%d\n"


.subsections_via_symbols
